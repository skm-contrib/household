<template>
  <div class="flex flex-col w-2/3 m-auto">
    <p class="text-6xl font-bold text-neutral-700 mb-12">Весь асортимент тут</p>

    <div
      class="gap-4 m-auto grid grid-cols-3 w-full mb-32 items-start align-top top-0"
    >
      <div
        class="w-full col-span-2 flex relative flex-grow gap-4 rounded-3x px-12"
      >
        <div
          class="grid grid-cols-3 w-auto align-baseline justify-center flex-col gap-2"
        >
          <div
            v-for="product in searchProducts"
            :key="product.id"
            class="w-full justify-center relative flex flex-col cursor-pointer p-4"
          >
            <div class="relative object group aspect-square">
              <img
                class="w-full object-cover block aspect-square rounded-2xl"
                :src="
                  'http://localhost:8001/api/v1/images/' + product.imagePath
                "
                alt=""
              />
              <div
                class="absolute rounded-2xl backdrop-filter backdrop-blur-md inset-0 bg-neutral-800 bg-opacity-30 opacity-0 group-hover:opacity-100 transition duration-300 ease-in-out flex items-center justify-center"
              >
                <p
                  class="text-white font-bold px-4 py-2 max-w-xs sm:max-w-sm md:max-w-md lg:max-w-lg xl:max-w-xl"
                >
                  {{ product.description }}
                </p>
              </div>
            </div>

            <div class="flex flex-grow flex-col text-neutral-700">
              <router-link
                :to="{ name: 'ProductPage', params: { id: product.id } }"
                class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
              >
                {{ product.name }}
              </router-link>
              <p class="mt-2 text-4xl text-blue-500 font-bold">
                {{ product.price }}₴
              </p>
              <div class="group cursor-pointer">
                <p
                  class="group-hover:text-emerald-800 my-2 duration-300 font-bold text-emerald-600 rounded-xl"
                >
                  {{ product.category.name }}
                </p>
              </div>
            </div>
            <div
              @click="addToBasket(product.id)"
              class="flex flex-col w-full m-auto justify-center"
            >
              <div
                class="mb-4 justify-center gap-4 group routerlink flex flex-row items-center cursor-pointer"
              >
                <button class="">Додати в кошик</button>
                <img
                  class="w-10 h-10 group-hover:bg-neutral-700 p-2 rounded-lg transition duration-300"
                  src="../../assets/icons/basket.png"
                  alt=""
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="flex flex-col top-0 justify-start align-top border-emerald-400 p-12 border-2 rounded-3xl bg-white bg-opacity-70 backdrop-filter backdrop-blur-lg"
      >
        <label class="text-sm text-gray-400" for=""
          >Знайдіть товар який вам потрібно</label
        >
        <div class="flex flex-row">
          <input
            class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
            type="text"
            v-model="name"
          />
        </div>
        <div class="my-4 w-full justify-center flex flex-col">
          <label class="text-sm text-gray-400" for=""
            >Вибір бажаної категорії</label
          >
          <select
            v-model="category_id"
            class="h-16 rounded-xl font-medium cursor-pointer border-2 shadow-xl border-emerald-500"
            @change="makeSearch"
          >
            <option
              v-for="category in categories"
              :value="category.id"
              :key="category.id"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
        <div class="flex flex-row justify-evenly pt-6">
          <div class="w-full">
            <VueSlider
              class="hidden"
              v-model="maxprice"
              :min="0"
              :max="15000"
              :tooltip="'always'"
            />
            <div class="text-center font-medium text-lg mt-6">
              <button class="routerlink w-32" @click="makeSearch">Пошук</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import restCategory from "../../composables/category";
import { onMounted, ref } from "vue";
import restProduct from "../../composables/product";
const { getCategories, categories } = restCategory();

import { useRoute } from "vue-router";
const { getProductsByNameAndPageNumberAndCategoryId, products } = restProduct();
const category_id = ref(0);
const page = ref(0);
const name = ref("");
const count = ref(100);
const route = useRoute();

const searchProducts = ref([]);

const makeSearch = async () => {
  await getProductsByNameAndPageNumberAndCategoryId(
    name.value,
    page.value,
    category_id.value,
    count.value
  );
  searchProducts.value = products.value;
};

const getAllCategoriesAndMakeSearch = async () => {
  await getCategories().then(() => {
    category_id.value = categories.value[0].id;
  });

  await makeSearch();
};

onMounted(() => {
  if (route.params.id != null) {
    category_id.value = route.params.id;
    console.log(category_id.value);
    makeSearch();
  } else {
    getAllCategoriesAndMakeSearch();
  }
});
</script>

<script>
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";

export default {
  data() {
    return {
      maxprice: 1500,
    };
  },
  components: {
    VueSlider,
  },
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
