<template>
  <div
    class="bottom-0 left-1/2 -translate-x-1/2 fixed bg-neutral-700 items-center bg-opacity-70 py-2 justify-evenly flex-col flex w-1/3 m-auto rounded-t-3xl backdrop-filter backdrop-blur-lg"
  >
    <div class="flex flex-row items-center justify-evenly gap-12">
      <div class="gap-2 flex flex-row text-lg text-white">
        <router-link class="link" :to="{ name: 'Home' }"
          >Якісь ссилки</router-link
        >
        <router-link class="link" :to="{ name: 'Home' }"
          >Якісь ссилки</router-link
        >
        <router-link class="link" :to="{ name: 'Home' }"
          >Якісь ссилки</router-link
        >
      </div>
      <div
        class="group hover:text-white cursor-pointer flex items-center gap-4 routerlink"
      >
        <router-link class="" :to="{ name: 'Home' }"
          >Зв'язатись з нами</router-link
        >
        <img
          class="w-10 h-10 group-hover:bg-neutral-700 p-2 rounded-lg transition duration-300"
          :src="mailIcon"
          alt=""
        />
      </div>
    </div>

    <div>
      <p class="text-sm text-neutral-900 mt-2 opacity-80">ntrcn</p>
    </div>
  </div>
</template>
<style>
.text-primary {
  color: var(red-);
}
</style>
<script>
import mailIcon from "@/assets/icons/mail.png";

export default {
  data() {
    return {
      mailIcon,
    };
  },
  props: {},
};
</script>
