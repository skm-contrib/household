<template>
  <div
    class="top-0 sm:left-1/2 flex-row sm:-translate-x-1/2 fixed bg-white items-center bg-opacity-70 py-2 sm:py-8 justify-evenly flex w-screen sm:w-2/3 m-auto rounded-b-3xl backdrop-filter backdrop-blur-lg"
  >
    <div class="gap-4 flex flex-col sm:flex-row text-lg">
      <router-link class="link" :to="{ name: 'Home' }">Головна</router-link>
      <router-link class="link" :to="{ name: 'Products' }">Магазин</router-link>
      <router-link
        v-if="user.role == 'ROLE_ADMIN'"
        class="link"
        :to="{ name: 'Admin' }"
        >ADMIN</router-link
      >
    </div>
    <div class="flex flex-row gap-4">
      <router-link
        :to="{ name: 'Profile' }"
        class="group cursor-pointer flex items-center gap-4 routerlink"
      >
        <div class="">Власний аккаунт<br /></div>
        <img
          class="w-10 h-10 group-hover:bg-neutral-700 p-2 rounded-lg transition duration-300"
          :src="userIcon"
          alt=""
        />
      </router-link>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import restUser from "@/composables/user";

const { getUser, user } = restUser();

onMounted(() => {
  getUser();
});
</script>

<script>
import userIcon from "@/assets/icons/userprofile.png";
export default {
  data() {
    return {
      userIcon,
    };
  },
  computed: {
    basketItemCount() {
      const userBasket = localStorage.getItem("userBasket");
      let parsedBasket = userBasket ? JSON.parse(userBasket) : [];
      return parsedBasket.length;
    },
  },
};
</script>
