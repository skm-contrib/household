<template>
  <div class="">
    <div class="top-0 my-12 flex items-center justify-center m-auto gap-4">
      <div class="p-4">
        <h1
          class="break-word font-bold text-4xl text-neutral-700 [text-shadow:_0_0px_20px_rgb(256_256_256_/_100%)]"
        >
          Інтернет-магазин
          <br /><span class="text-6xl font-black text-emerald-700"
            >побутової хімії</span
          >
          <br />повного циклу замовлення
        </h1>
      </div>
      <div class="relative">
        <img
          class="m-4 h-72 relative rounded-3xl hover:-translate-y-3 hover:rotate-2 cursor-pointer duration-300"
          src="../../assets/pagesimages/mainimage.jpg"
          alt=""
        />
      </div>
    </div>

    <div
      v-if="products"
      class="w-2/3 grid gap-4 grid-cols-3 border-2 border-white bg-white rounded-3xl m-auto bg-opacity-70 justify-evenly backdrop-filter backdrop-blur-lg"
    >
      <div
        class="flex bg-emerald-200 rounded-xl m-2 p-2 pt-12 flex-col gap-12"
        v-for="product in products"
        :key="product.id"
      >
        <div class="relative cursor-pointer flex flex-col group">
          <img
            class="m-4 h-96 object-cover rounded-3xl"
            :src="'http://localhost:8001/api/v1/images/' + product.imagePath"
            alt="no img"
          />
          <div
            class="absolute rounded-3xl backdrop-filter backdrop-blur-md inset-0 bg-neutral-800 m-4 bg-opacity-30 opacity-0 group-hover:opacity-100 transition duration-300 ease-in-out flex items-center justify-center"
          >
            <p
              class="text-white font-bold px-4 py-2 max-w-xs sm:max-w-sm md:max-w-md lg:max-w-lg xl:max-w-xl"
            >
              {{ product.description }}
            </p>
          </div>
        </div>
        <div class="flex flex-col text-neutral-700">
          <div
            class="backdrop-filter backdrop-blur-xl group cursor-pointer absolute left-0 ml-56 routerlink"
          >
            <router-link
              :to="{ name: 'Products', params: { id: product.category.id } }"
              class="group-hover:text-emerald-800 font-bold text-emerald-400 p-2 rounded-xl"
            >
              {{ product.category.name }}
            </router-link>
          </div>

          <router-link
            :to="{ name: 'ProductPage', params: { id: product.id } }"
            class="duration-300 hover:text-neutral-500 break-word font-bold text-4xl text-neutral-700"
          >
            {{ product.name }}
          </router-link>
          <p class="mt-2 text-2xl text-blue-700 font-bold">
            {{ product.price }}₴
          </p>
          <p class="mt-2 text-md">{{ product.description }}</p>

          <div
            class="flex flex-col w-full m-auto justify-center"
            @click="addToBasket(product.id)"
          >
            <div
              class="py-4 my-4 justify-center gap-4 group routerlink flex flex-row items-center cursor-pointer"
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
    <div class="flex mb-32 gap-4 mt-4 text-3xl m-auto w-2/3 flex-row">
      <p class="cursor-pointer" @click="goToPreviousPage">Попередня</p>
      <p
        class="cursor-pointer"
        @click="goToPage(page)"
        v-for="page in allPages"
        :key="page.id"
      >
        {{ page }}
      </p>
      <p class="cursor-pointer" @click="goToNextPage">Наступна</p>
    </div>
  </div>
</template>

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

<script setup>
import { onMounted, ref } from "vue";

import restProduct from "../../composables/product";

const { getProductsByPageNumber, getProductsCount, productsCount, products } =
  restProduct();

let pageNumber = 0;
const productsOnPage = 6;
const allPages = ref(0);

const getAllPages = async () => {
  allPages.value = Math.ceil(productsCount.value / productsOnPage);
};

const goToPreviousPage = () => {
  if (pageNumber > 0) {
    pageNumber--;
  }
  changePage();
};

const goToNextPage = () => {
  if (pageNumber < allPages.value - 1) {
    pageNumber++;
  }
  changePage();
};

const goToPage = (page) => {
  page--;
  if (page >= 0 && page < allPages.value) {
    pageNumber = page;
  }
  changePage();
};

const changePage = async () => {
  await getProductsByPageNumber(pageNumber, productsOnPage);
};

onMounted(async () => {
  await getProductsCount();
  await changePage();
  await getAllPages();
});
</script>
