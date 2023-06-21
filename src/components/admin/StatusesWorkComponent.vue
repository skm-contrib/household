<template>
  <div>
    <div>
      <h1 class="text-4xl mb-12 font-bold text-neutral-600 text-center">
        Статуси замовлення
      </h1>

      <div class="gap-4 flex flex-col">
        <h2 class="text-xl font-bold text-neutral-600">
          Створити новий статус
        </h2>
        <div class="flex w-full flex-row align-middle items-center gap-4">
          <div class="w-full">
            Назва
            <input
              class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
              type="text"
              v-model="form.name"
            />
            Колір
            <input
              class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
              type="text"
              v-model="form.color"
            />
          </div>
          <div
            @click="createOrderStatus(form)"
            class="h-16 routerlink bg-emerald-500 border-emerald-500 hover:text-emerald-500 hover:border-emerald-500 w-1/3 align-middle items-center justify-center flex"
          >
            Додати
          </div>
        </div>

        <h2 class="pt-12 text-xl font-bold text-neutral-600">
          Редагувати наявні
        </h2>
        <div class="grid grid-cols-1 gap-4 mb-64">
          <div
            class="flex w-full flex-row align-middle items-center gap-4 border-b-8 pb-4"
            v-for="order_status in order_statuses"
            :key="order_status.name"
          >
            <div class="w-full flex flex-col gap-2">
              <div class="flex flex-row items-center gap-2">
                <p class="text-xl text-neutral-700 font-bold">Назва</p>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model.lazy="order_status.name"
                />
              </div>
              <div class="flex flex-row items-center gap-2">
                <p class="text-xl text-neutral-700 font-bold">Колір</p>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model.lazy="order_status.color"
                />
              </div>
            </div>
            <div
              @click="updateOrderStatus(order_status)"
              class="h-16 routerlink bg-emerald-500 border-emerald-500 hover:text-emerald-500 hover:border-emerald-500 w-1/3 align-middle items-center justify-center flex"
            >
              Зберегти
            </div>
            <div
              @click="deleteOrderStatus(order_status.id)"
              class="h-16 routerlink bg-red-500 border-red-500 hover:text-red-500 hover:border-red-500 w-1/3 align-middle items-center justify-center flex"
            >
              Видалити
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<script setup>
import { onMounted, reactive } from "vue";

import restOrderStatus from "@/composables/order_status";
const form = reactive({
  name: "",
  color: "",
});
const {
  createOrderStatus,
  getOrderStatuses,
  updateOrderStatus,
  deleteOrderStatus,
  order_statuses,
} = restOrderStatus();

onMounted(() => {
  getOrderStatuses();
});
</script>
