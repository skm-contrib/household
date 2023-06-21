<template>
  <div class="flex flex-col sm:w-2/3 m-auto">
    <div
      class="flex m-4 sm:m-0 flex-row justify-between bg-white p-6 bg-opacity-70 backdrop-filter backdrop-blur-lg rounded-3xl mb-12"
    >
      <div class="m-auto w-2/3 flex flex-col gap-2">
        <p class="sm:text-6xl text-2xl font-bold text-neutral-700">
          Привіт, {{ user.name }}
        </p>
        <p class="sm:text-3xl font-medium text-neutral-700">
          Ваш кошик виглядає наступним чином:
        </p>
      </div>
      <div
        class="flex flex-col items-center justify-around gap-4 m-4 border-l-4 pl-4 border-neutral-700"
      >
        <router-link
          :to="{ name: 'UserEdit' }"
          class="routerlink cursor-pointer text-center bg-emerald-500 border-emerald-500 hover:border-emerald-500 hover:text-emerald-500"
          >Редагувати профіль</router-link
        >
        <div
          @click="logout"
          class="routerlink cursor-pointer text-center bg-red-500 border-red-500 hover:border-red-500 hover:text-red-500"
        >
          Вийти з аккаунту
        </div>
      </div>
    </div>
    <div
      v-if="userBasketProducts.length == 0"
      class="w-fit m-auto flex justify-center flex-col border-emerald-400 border-2 rounded-3xl bg-white bg-opacity-70 backdrop-filter backdrop-blur-lg p-4"
    >
      <div class="flex m-auto flex-row w-full p-4 gap-12">
        <div class="flex flex-col">
          <div>
            <p class="font-black text-4xl text-neutral-600">Кошик порожній</p>
          </div>
        </div>
      </div>
    </div>
    <div
      class="gap-4 p-4 sn:p-0 w-auto m-auto flex flex-col-reverse sm:flex-row mb-32 items-start align-top top-0"
    >
      <div
        v-if="userBasketProducts.length != 0"
        class="sm:mx-12 w-full sm:w-auto justify-evenly pt-12 flex-col gap-4 border-emerald-400 border-2 rounded-3xl bg-white bg-opacity-70 backdrop-filter backdrop-blur-lg p-4"
      >
        <div class="flex flex-col sm:flex-row w-full p-4 gap-12">
          <div class="flex flex-col">
            <div
              class="flex flex-col text-neutral-700"
              v-for="product in userBasketProducts"
              :key="product"
            >
              <div
                class="grid grid-cols-2 sm:grid-cols-5 justify-between items-center gap-8"
              >
                <img
                  class="w-32 h-32 object-cover"
                  :src="
                    'http://localhost:8001/api/v1/images/' + product.imagePath
                  "
                  alt=""
                />
                <router-link
                  :to="{ name: 'ProductPage', params: { id: product.id } }"
                  class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
                >
                  {{ product.name }}
                </router-link>
                <p class="mt-2 text-2xl text-blue-500 font-bold">
                  {{ product.price }}₴
                </p>
                <div class="group cursor-pointer">
                  <router-link
                    :to="{
                      name: 'Products',
                      params: { id: product.category.id },
                    }"
                    class="group-hover:text-emerald-800 font-bold text-emerald-400 p-2 rounded-xl"
                  >
                    {{ product.category.name }}
                  </router-link>
                </div>

                <div
                  class="flex flex-row gap-4 items-center font-bold text-neutral-700 text-6xl"
                >
                  <h2 class="">
                    <span class="text-3xl">x</span>{{ product.count }}
                  </h2>
                  <div
                    class="flex gap-2 flex-col justify-center items-center text-4xl"
                  >
                    <p
                      @click="incrementCount(product.product_id)"
                      class="text-emerald-500 hover:bg-emerald-500 rounded-full p-2 duration-300 cursor-pointer hover:text-white"
                    >
                      +
                    </p>
                    <p
                      @click="decrementCount(product.product_id)"
                      class="text-red-500 hover:bg-red-500 rounded-full p-2 duration-300 cursor-pointer hover:text-white"
                    >
                      -
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="flexflex-col w-full top-0 sm:w-1/3 justify-start align-top border-emerald-400 sm:p-12 border-2 rounded-3xl bg-white bg-opacity-70 backdrop-filter backdrop-blur-lg"
        v-if="userBasketProducts.length != 0"
      >
        <div
          class="py-4 my-4 m-4 sm:m-0 justify-center gap-4 group routerlink flex flex-row items-center cursor-pointer"
        >
          <router-link class="" :to="{ name: 'Payout' }">ОПЛАТИТИ</router-link>
          <img
            class="w-10 h-10 group-hover:bg-neutral-700 p-2 rounded-lg transition duration-300"
            src="../../assets/icons/basket.png"
            alt=""
          />
        </div>
        <div class="flex flex-row justify-evenly">
          <img class="h-4" src="../../assets/paysystems/apay.png" alt="" />
          <img class="h-4" src="../../assets/paysystems/gpay.png" alt="" />
          <img class="h-4" src="../../assets/paysystems/mr.png" alt="" />
          <img class="h-4" src="../../assets/paysystems/paypal.png" alt="" />
          <img class="h-4" src="../../assets/paysystems/visa.png" alt="" />
        </div>
        <div class="m-4 sm:m-0">
          <p class="font-bold text-neutral-400">
            Сума до сплати: <br /><span class="text-2xl text-neutral-600">
              {{ sumToPay }}
            </span>
          </p>
        </div>
      </div>
    </div>
    <div>
      <h2 class="font-bold m-4 sm:m-0 text-2xl text-neutral-700">
        Історія замовлень
      </h2>
    </div>
    <div class="flex m-4 sm:m-0 flex-col gap-4 mt-4 mb-32">
      <div
        class="flex flex-col justify-between bg-white p-4 rounded-xl"
        v-for="order in orders"
        :key="order.id"
      >
        <div
          class="flex flex-col sm:flex-row justify-between"
          v-for="product in order.products"
          :key="product.id"
        >
          <div>
            <img
              class="m-4 h-32 rounded-3xl"
              :src="
                'https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/images/' +
                product.product.imagePath
              "
              alt=""
            />
          </div>
          <div class="flex flex-col text-neutral-700">
            <router-link
              :to="{ name: 'ProductPage', params: { id: product.product.id } }"
              class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
            >
              {{ product.product.name }}
            </router-link>
            <p class="mt-2 text-2xl text-blue-500 font-bold">
              {{ order.price }}₴
            </p>
            <div class="group cursor-pointer">
              <router-link
                :to="{
                  name: 'Products',
                  params: { id: product.product.category?.id },
                }"
                class="group-hover:text-emerald-800 duration-300 font-bold text-emerald-400 py-4 rounded-xl"
              >
                {{ product.product.category.name }}
              </router-link>
            </div>
          </div>

          <div
            class="flex flex-col gap-4 items-center font-bold text-neutral-700 text-6xl p-4 rounded-xl"
          >
            <p class="text-lg">Кількість</p>
            <h2 class=""><span class="text-3xl">x</span>{{ product.count }}</h2>
          </div>
        </div>
      </div>
    </div>

    <div
      class="w-fit m-auto flex justify-center flex-col border-emerald-400 border-2 rounded-3xl bg-white bg-opacity-70 backdrop-filter backdrop-blur-lg p-4"
    >
      <div class="flex m-auto flex-row w-full p-4 gap-12">
        <div class="flex flex-col">
          <div v-if="orders.length == 0">
            <p class="font-black text-4xl text-neutral-600">
              Замовлення відсутні
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import restAuth from "../../composables/auth";
import restUser from "@/composables/user";
import restProduct from "@/composables/product";
import restOrders from "@/composables/orders";

const { getUserOrders, orders } = restOrders();

const { logout, userLogged } = restAuth();
const { getProducts, products } = restProduct();
const { getUser, user } = restUser();
const userBasket = ref([]);
const userBasketProducts = ref([]);

const getBasket = async () => {
  userBasket.value = localStorage.getItem("userBasket");

  userBasket.value = userBasket.value ? JSON.parse(userBasket.value) : [];
  console.log(userBasket.value);

  await getProducts();

  for (const prod of userBasket.value) {
    let product = products.value.find((p) => p.id === prod.product_id);
    if (product) {
      userBasketProducts.value.push({ ...product, ...prod });
    } else {
      console.log(`Нема продукту з таким айді: ${prod.product_id}`);
    }
  }
  checkSumToPay();
};

const incrementCount = async (product_id) => {
  const product = userBasketProducts.value.find(
    (prod) => prod.product_id === product_id
  );
  if (product) {
    product.count += 1;
  }

  updateUserBasket();
};

const decrementCount = async (product_id) => {
  const product = userBasketProducts.value.find(
    (prod) => prod.product_id === product_id
  );
  if (product) {
    if (product.count > 1) {
      product.count -= 1;
    } else {
      const index = userBasketProducts.value.findIndex(
        (prod) => prod.product_id === product_id
      );
      if (index !== -1) {
        userBasketProducts.value.splice(index, 1);
      }
    }
  }

  updateUserBasket();
};

const updateUserBasket = () => {
  userBasket.value = JSON.stringify(userBasketProducts.value);
  localStorage.setItem("userBasket", userBasket.value);
  checkSumToPay();
};

const sumToPay = ref(0);

const checkSumToPay = () => {
  sumToPay.value = 0;
  userBasketProducts.value.forEach((productInBasket) => {
    sumToPay.value += productInBasket.price * productInBasket.count;
  });
};

onMounted(() => {
  getBasket();
  userLogged();
  getUserOrders();
  getUser();
});
</script>

<script>
export default {
  methods: {},
};
</script>
