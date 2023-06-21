<template>
  <div class="flex flex-col sm:flex-row sm:w-2/3 m-auto justify-between gap-4">
    <div class="flex flex-col" v-if="product">
      <div class="grid sm:grid-cols-3 gap-12">
        <div>
          <div
            class="backdrop-filter border-0 bg-white bg-opacity-40 backdrop-blur-sm group cursor-pointer absolute routerlink m-2"
          >
            <router-link
              :to="{ name: 'Products', params: { id: product.category?.id } }"
              class="group-hover:text-emerald-800 duration-300 font-bold text-neutral-700 p-2 rounded-xl"
            >
              {{ product.category?.name }}
            </router-link>
          </div>
          <img
            class="rounded-3xl p-4 sm:p-0"
            :src="'http://localhost:8001/api/v1/images/' + product.imagePath"
            alt=""
          />
        </div>
        <div
          class="flex sm:col-span-2 sm:p-12 p-4 mx-4 sm:mx-0 flex-col bg-white border-2 border-white rounded-3xl text-neutral-700 bg-opacity-70 backdrop-filter backdrop-blur-lg"
        >
          <h1 class="break-word font-bold text-4xl text-neutral-700">
            {{ product.name }}
          </h1>
          <p class="mt-2 text-blue-700 text-4xl font-bold">
            {{ product.price }}₴
          </p>

          <div class="flex flex-col w-full m-auto justify-center">
            <div>
              <div
                class="py-4 my-4 justify-center gap-4 group routerlink flex flex-row items-center cursor-pointer"
              >
                <button class="" @click="addToBasket(product.id)">
                  Додати в кошик
                </button>
                <img
                  class="w-10 h-10 group-hover:bg-neutral-700 p-2 rounded-lg transition duration-300"
                  src="../../assets/icons/basket.png"
                  alt=""
                />
              </div>
              <div class="flex flex-row justify-evenly">
                <img
                  class="h-4"
                  src="../../assets/paysystems/apay.png"
                  alt=""
                />
                <img
                  class="h-4"
                  src="../../assets/paysystems/gpay.png"
                  alt=""
                />
                <img class="h-4" src="../../assets/paysystems/mr.png" alt="" />
                <img
                  class="h-4"
                  src="../../assets/paysystems/paypal.png"
                  alt=""
                />
                <img
                  class="h-4"
                  src="../../assets/paysystems/visa.png"
                  alt=""
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="flex m-4 sm:m-0 justify-center mt-12 flex-row w-full">
        <div
          class="w-full flex p-12 flex-col bg-white border-2 border-white rounded-3xl text-neutral-700 bg-opacity-70 backdrop-filter backdrop-blur-lg"
        >
          <div class="flex flex-row items-center gap-4">
            <h2
              class="break-word text-left font-bold text-4xl text-neutral-700"
            >
              Опис продукуту
            </h2>

            <img class="h-12" src="../../assets/icons/info.png" alt="" />
          </div>

          <div class="mt-8 flex gap-12 items-center">
            <div class="px-4">
              <p class="font-bold text-neutral-600 text-2xl">
                {{ product.description }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div
      class="flex m-4 sm:m-0 sm:p-12 sm:w-1/3 flex-col bg-white border-2 border-white rounded-3xl text-neutral-700 bg-opacity-70 backdrop-filter backdrop-blur-lg"
    >
      <h1 class="break-word font-bold text-4xl p-4 sm:p-0 text-neutral-700">
        Відгуки
      </h1>
      <div class="overflow-visible m-4 sm:m-0">
        <div
          v-for="review in product.reviews"
          :key="review.id"
          class="scroll overflow-visible"
        >
          <div
            v-if="review.text != null"
            class="flex overflow-visible flex-col my-2"
          >
            <div class="flex flex-row border-2 rounded-xl items-center gap-4">
              <div>
                <div class="flex flex-row bg-neutral-200 rounded-l-md p-2">
                  <h2 class="text-xl font-bold">
                    {{ review.reviewOwner.name }}
                  </h2>
                  <div class="flex gap-2">
                    <p>{{ review.reviewOwner.surname }}</p>
                    <p>{{ review.reviewOwner.patronymic }}</p>
                  </div>
                </div>
              </div>
              <div>{{ review.text }}</div>
            </div>

            <h2>{{ formatDate(review.timeOfReview) }}</h2>
          </div>
        </div>
      </div>
      <div class="bottom-4 sm:absolute mx-4 left-0 right-0">
        <label class="text-sm text-gray-400"
          >Напишіть вашу думку про даний продукт</label
        >
        <div class="flex flex-row">
          <input
            class="w-full rounded-r-none border-r-0 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
            type="text"
            v-model="form.text"
          />
          <button class="routerlink rounded-l-none" @click="createReview(form)">
            Відправити
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, watch } from "vue";
import { useRoute } from "vue-router";
import restReview from "@/composables/review";
import restProduct from "../../composables/product";
const router = useRoute();
const { createReview } = restReview();
const { getProduct, product } = restProduct();

const form = reactive({
  productId: undefined,
  text: "",
  jwt: localStorage.getItem("token"),
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleString();
};

const getProductInfo = async () => {
  await getProduct(router.params.id);
};

watch(product, (newValue) => {
  form.productId = newValue.id;
});
onMounted(() => {
  getProductInfo();
});
</script>
<script>
export default {
  methods: {
    addToBasket(id) {
      const productToBasket = {
        product_id: id,
        count: 1,
      };
      const userBasket = localStorage.getItem("userBasket");
      let parsedBasket = userBasket ? JSON.parse(userBasket) : [];

      const index = parsedBasket.indexOf(id);
      if (index === -1) {
        parsedBasket.push(productToBasket);
      } else {
        parsedBasket.splice(index, 1);
      }

      localStorage.setItem("userBasket", JSON.stringify(parsedBasket));
    },
  },
};
</script>
