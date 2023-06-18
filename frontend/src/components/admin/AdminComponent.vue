<template>
  <div v-if="user.role == 'ROLE_ADMIN'" class="flex flex-col w-2/3 m-auto">
    <div
      class="flex flex-row justify-evenly bg-white p-6 bg-opacity-70 backdrop-filter backdrop-blur-lg rounded-3xl mb-12"
    >
      <div
        class="w-full"
        v-for="(category, index) in choosedCategory"
        :key="category.id"
      >
        <div
          @click="changeSelected(index)"
          :class="[category.enabled ? 'routerlink selected' : 'routerlink']"
          class="text-center py-4 m-2"
        >
          <div class="cursor-pointer">
            {{ category.name }}
          </div>
        </div>
      </div>
    </div>
    <component :is="choosedCategory[selectedIndex].component"></component>
  </div>

  <div class="w-2/3 flex items-center text-center justify-center m-auto" v-else>
    <h1 class="text-6xl font-black">403 FORBIDDEN</h1>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import restUser from "@/composables/user";
const { getUser, user } = restUser();

const getCurrentUser = async () => {
  await getUser();
};

onMounted(async () => {
  await getCurrentUser();
});
</script>

<script>
import UsersOrders from "./UsersOrders.vue";
import AllProducts from "./AllProducts.vue";
import CreateProduct from "./CreateProduct.vue";
import CategoriesWorkComponent from "./CategoriesWorkComponent.vue";
import StatusesWorkComponent from "./StatusesWorkComponent.vue";
export default {
  data() {
    return {
      selectedIndex: 0,
      choosedCategory: [
        {
          name: "Замовлення користувачів",
          enabled: true,
          component: UsersOrders,
        },
        {
          name: "Наявні продукти",
          enabled: false,
          component: AllProducts,
        },
        {
          name: "Створення продукту",
          enabled: false,
          component: CreateProduct,
        },
        {
          name: "Категорії продуктів",
          enabled: false,
          component: CategoriesWorkComponent,
        },
        {
          name: "Статуси замовлення",
          enabled: false,
          component: StatusesWorkComponent,
        },
      ],
    };
  },
  components: {
    UsersOrders,
    AllProducts,
    CreateProduct,
  },
  methods: {
    changeSelected(index) {
      this.choosedCategory.forEach((category) => {
        category.enabled = false;
      });
      this.choosedCategory[index].enabled = true;
      this.selectedIndex = index;
    },
  },
};
</script>
