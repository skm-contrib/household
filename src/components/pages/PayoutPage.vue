<template>
  <div class="m-auto sm:w-2/3 p-4 sm:p-0 flex justify-center shadow-lg">
    <div class="grid sm:grid-cols-2 h-full w-full">
      <div
        class="flex flex-col justify-center bg-neutral-900 h-full w-full p-2 sm:p-12"
      >
        <div class="text-center">
          <h1 class="break-word text-lg text-neutral-500">
            Інтернет-магазин побутової хімії повного циклу замовлення
          </h1>
        </div>
        <div>
          <div
            class="flex my-4 sm:my-12 flex-row justify-between text-sm sm:text-xl font-bold text-neutral-400 border-b-2 border-neutral-600"
          >
            <p>Назва продукту</p>
            <p>Ціна та кількість</p>
          </div>
        </div>
        <div class="flex-col h-full w-full">
          <div
            class="flex flex-col sm:mt-2 text-neutral-100"
            v-for="product in userBasketProducts"
            :key="product"
          >
            <div
              class="flex flex-row justify-between w-full items-center gap-8"
            >
              <div class="flex text-sm sm:text-md flex-row gap-4 items-center">
                <p>
                  {{ product.name }}
                </p>

                <div>
                  <p>
                    {{ product.category.name }}
                  </p>
                </div>
              </div>

              <div class="flex flex-row gap-4 items-center">
                <p class="font-bold text-sm sm:text-xl">{{ product.price }}₴</p>
                <h2 class="">
                  x<span class="text-2xl font-bold">{{ product.count }}</span>
                </h2>
              </div>
            </div>
          </div>
        </div>
        <div
          class="flex text-white mt-12 flex-row w-full justify-between border-t-2 border-neutral-600 gap-4 items-center"
        >
          <div class="text-xl text-bold">Сума до сплати</div>
          <div class="text-2xl font-black">{{ sumToPay }}₴</div>
        </div>
      </div>
      <div
        class="flex flex-col gap-12 justify-center bg-neutral-100 h-full w-full p-12"
      >
        <div>
          <h1 class="font-bold text-3xl text-neutral-700">Оплата картою</h1>
          <div
            class="flex mt-4 flex-row w-full bg-neutral-200 p-2 rounded-xl border-2 border-neutral-300"
          >
            <p class="text-neutral-600 w-1/3">Пошта</p>
            <p>{{ user.email }}</p>
          </div>

          <p class="mt-4 text-neutral-600">Інформація карти</p>
          <div
            class="flex flex-col w-full bg-neutral-100 rounded-xl border-2 border-neutral-200"
          >
            <input
              v-model="form.cardNumber"
              placeholder="1234 1234 1234 1234"
              type="text"
              class="bg-transparent border-b-2 p-2 focus:outline-none focus:border-neutral-500 focus:border-b-2 border-neutral-200"
              name=""
              maxlength="16"
              id=""
              @input="handleCardNumberInput"
            />
            <div class="flex flex-row">
              <input
                placeholder="ММ / РР"
                type="text"
                pattern="\d{2} \/ \d{2}"
                class="bg-transparent w-full p-2 border-r-2 focus:outline-none focus:border-neutral-500 focus:border-b-2 focus:border-r-neutral-200 border-neutral-200"
                name=""
                id=""
                maxlength="7"
                @input="formatExpirationDate"
              />
              <input
                v-model="form.cvv"
                placeholder="CVV"
                type="numeric"
                class="bg-transparent w-full focus:outline-none focus:border-neutral-500 focus:border-b-2 p-2"
                name=""
                id=""
                maxlength="3"
                @input="handleCVCTyping"
              />
            </div>
          </div>

          <p class="mt-4 text-neutral-600">Ім'я на карті</p>
          <input
            v-model="form.cardHolderName"
            type="text"
            class="bg-transparent rounded-xl w-full border-2 p-2 focus:outline-none focus:border-neutral-500 focus:border-b-2 border-neutral-200"
            name=""
            id=""
          />

          <p class="mt-4 text-neutral-600">Адреса</p>
          <input
            v-model="form.address"
            class="bg-transparent rounded-xl w-full border-2 p-2 focus:outline-none focus:border-neutral-500 focus:border-b-2 border-neutral-200"
            type="text"
            name=""
            id=""
          />

          <div class="m-auto flex justify-center pt-4">
            {{ errors }}
            <button
              @click="createOrder(form)"
              class="from-emerald-400 w-full bg-gradient-to-r items-center to-emerald-500 rounded-md shadow-lg hover:-translate-y-1 duration-300 transition-all hover:shadow-md"
            >
              <p class="text-2xl m-2 font-bold text-white">Оплатити</p>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import restOrders from "@/composables/orders";

import { onMounted, ref, reactive } from "vue";

import restUser from "@/composables/user";
import restProduct from "@/composables/product";
const { createOrder, errors } = restOrders();

const formatExpirationDate = (event) => {
  const input = event.target;
  const value = input.value.replace(/\D/g, "").slice(0, 4);
  const month = value.slice(0, 2);
  const year = value.slice(2, 4);

  const expDate = ref([]);
  expDate.value = `${month} / ${year}`;
  input.value = expDate.value;

  const [m, y] = expDate.value.split(" / ");
  const convertedDate = `20${y}-${m.padStart(2, "0")}-01`;

  form.expirationDate = convertedDate;
};

const handleCVCTyping = (event) => {
  const input = event.target;
  const cleanedValue = input.value.replace(/\D/g, "");
  input.value = cleanedValue;
};

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

const sumToPay = ref(0);

const checkSumToPay = () => {
  sumToPay.value = 0;
  userBasketProducts.value.forEach((productInBasket) => {
    sumToPay.value += productInBasket.price * productInBasket.count;

    let productToBuy = {
      productId: productInBasket.id,
      count: productInBasket.count,
    };

    form.productsToBuy.push(productToBuy);
  });
};

const form = reactive({
  cardNumber: null,
  expirationDate: null,
  cvv: null,
  productsToBuy: [],
});

onMounted(() => {
  getBasket();
  getUser();
});
</script>
