<template>
  <div
    class="flex flex-row justify-between bg-white p-6 bg-opacity-70 backdrop-filter backdrop-blur-lg rounded-3xl mb-12"
  >
    <div class="m-auto p-4 sm:p-0 sm:w-2/3 flex flex-col gap-2">
      <p class="sm:text-6xl text-2xl font-bold text-neutral-700 text-center">
        Замовлення користувачів
      </p>
    </div>
    <div
      class="flex flex-col items-center justify-around gap-4 m-4 pl-4 border-neutral-700"
    ></div>
  </div>
  <div
    v-for="order in orders"
    :key="order.id"
    class="flex justify-between flex-col items-center sm:flex-row sm:gap-24"
  >
    <div class="flex flex-col">
      <p class="text-4xl font-bold text-neutral-700 py-2">
        {{ order.orderOwner.name }}
      </p>
      <div class="flex text-2xl font-bold flex-row gap-2">
        <p>{{ order.orderOwner.surname }}</p>
        <p>{{ order.orderOwner.patronymic }}</p>
      </div>

      <div v-for="product in order.products" :key="product">
        <div
          class="flex justify-between flex-col items-center sm:flex-row sm:gap-24"
        >
          <div>
            <img
              class="m-4 h-32 w-32 object-cover rounded-3xl"
              :src="
                'https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/images/' +
                product.product.imagePath
              "
              alt=""
            />
          </div>
          <div class="flex flex-col text-neutral-700">
            <router-link
              :to="{ name: 'ProductPage' }"
              class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
            >
              {{ product.product.name }}
            </router-link>
            <p class="mt-2 text-2xl text-blue-500 font-bold">
              {{ product.product.price }}₴
            </p>
            <div class="group cursor-pointer">
              <router-link
                :to="{
                  name: 'Products',
                  params: { id: product.product.category?.id },
                }"
                class="group-hover:text-emerald-800 duration-300 font-bold text-emerald-400 py-4 rounded-xl"
              >
                {{ product.product.category?.name }}
              </router-link>
            </div>
          </div>
          <div
            class="flex flex-col gap-4 items-center font-bold text-neutral-700 text-6xl p-4 rounded-xl"
          >
            <p class="text-lg">Кількість</p>
            <h2 class="">
              <span class="text-3xl">x</span>
              {{ product.count }}
            </h2>
          </div>
        </div>
      </div>

      <div class="flex flex-col justify-evenly gap-4 text-center">
        <div class="w-full justify-center flex flex-col">
          <h3 class="py-2 text-neutral-700 text-start font-bold text-2xl">
            Статус замовлення
          </h3>
          <div class="flex w-full flex-row align-middle items-center gap-4">
            <div class="w-full">
              <select
                :style="{ 'background-color': order.status.color }"
                v-model="order.status.id"
                name=""
                id=""
                class="h-16 w-full rounded-xl font-medium cursor-pointer border-2 shadow-xl border-emerald-500"
              >
                <option
                  class="font-bold text-white text-xl bg-neutral-700"
                  v-for="order_status in order_statuses"
                  :key="order_status.id"
                  :value="order_status.id"
                >
                  <p class="font-bold text-white text-xl">
                    {{ order_status.name }}
                  </p>
                </option>
              </select>
            </div>
            <div
              @click="updateOrderStatus(order.id, order.status.id)"
              class="h-16 routerlink w-1/3 align-middle items-center justify-center flex"
            >
              Зберегти
            </div>
          </div>
        </div>
        <div
          class="routerlink cursor-pointer shadow-xl bg-red-500 hover:border-red-500 border-red-500 h-16 align-middle items-center justify-center flex hover:text-red-500"
        >
          Видалити
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import restOrderStatus from "@/composables/order_status";
import restOrders from "@/composables/orders";

const { getOrders, orders, updateOrderStatus } = restOrders();
const { getOrderStatuses, order_statuses } = restOrderStatus();

const getAllProductsAndStatuses = async () => {
  await getOrderStatuses();
  await getOrders();
};

onMounted(() => {
  getAllProductsAndStatuses();
});
</script>
