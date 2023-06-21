<template>
  <div class="relative">
    <HeaderComponent class="z-50" />
    <div class="mt-48">
      <RouterView />
    </div>
    <!--<FooterComponent class="z-50"/>-->
  </div>
</template>

<script setup></script>

<script>
//import FooterComponent from "./FooterComponent.vue";
import HeaderComponent from "./HeaderComponent.vue";
export default {
  name: "App",
  components: {
    HeaderComponent,
    //FooterComponent,
  },
};
</script>
