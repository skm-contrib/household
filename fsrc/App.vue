<template>
  <!--<img alt="Vue logo" src="./assets/logo.png">-->
  <div class="parallax m-auto justify-center">
    <img src="" data-type="background" v-parallax />
  </div>

  <MainLayout />
</template>

<script>
import MainLayout from "./components/pageelements/MainLayout.vue";

export default {
  name: "App",
  components: {
    MainLayout,
  },
};
</script>

<style>
body {
  background-color: #f4f1ed;
}
</style>
