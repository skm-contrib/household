<template>
  <div class="mb-44">
    <div
      v-for="product in products"
      :key="product.id"
      class="flex mb-4 flex-col sm:flex-row justify-between bg-white sm:p-6 bg-opacity-70 backdrop-filter backdrop-blur-lg rounded-3xl"
    >
      <div class="flex w-auto flex-row p-4 gap-24 border-emerald-400">
        <div>
          <img
            class="h-44 w-44 object-cover rounded-3xl"
            :src="
              'https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/images/' +
              product.imagePath
            "
            alt=""
          />
        </div>

        <div class="flex flex-col text-neutral-700">
          <router-link
            :to="{ name: 'ProductPage', params: { id: product.id } }"
            class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
          >
            {{ product.name }}
          </router-link>

          <p class="mt-2 text-2xl text-blue-500 font-bold">
            {{ product.price }}₴
          </p>

          <p class="mt-2 text-2xl text-neutral-500">
            {{ product.description }}
          </p>

          <div class="group cursor-pointer">
            <router-link
              :to="{ name: 'Products', params: { id: product.category?.id } }"
              class="group-hover:text-emerald-800 duration-300 font-bold text-emerald-400 py-4 rounded-xl"
            >
              {{ product.category.name }}
            </router-link>
          </div>
        </div>
      </div>
      <div
        class="flex flex-col items-center justify-around gap-4 m-4 border-l-4 pl-4 border-neutral-700"
      >
        <router-link
          :to="{ name: 'EditProduct', params: { id: product.id } }"
          class="routerlink cursor-pointer bg-emerald-500 hover:border-emerald-500 border-emerald-500 hover:text-emerald-500"
          >Редагувати продукт</router-link
        >
        <div
          @click="deleteProduct(product.id)"
          class="routerlink cursor-pointer bg-red-500 border-red-500 hover:border-red-500 hover:text-red-500"
        >
          Видалити з бази даних
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";

import restProduct from "../../composables/product";

const { getProducts, deleteProduct, products } = restProduct();

onMounted(() => {
  getProducts();
});
</script>
