<template>
  <div class="flex flex-col m-auto justify-center sm:w-2/3">
    <div
      class="sm:w-1/3 gap-4 border-2 border-white bg-white rounded-3xl m-auto flex flex-col bg-opacity-70 justify-evenly backdrop-filter backdrop-blur-lg"
    >
      <h1 class="text-4xl mt-12 font-bold text-neutral-600 text-center">
        Авторизація
      </h1>
      <div class="grow flex flex-col text-center justify-center">
        <div class="text-left">
          <form @submit.prevent="">
            <div class="flex flex-col rounded-2xl">
              <div class="flex flex-col m-4 p-4 gap-2 rounded-2xl">
                <h3 class="text-neutral-700 font-bold text-2xl">
                  Ваша електронна пошта
                </h3>
                <label class="text-neutral-500 text-sm"
                  >Для доступу в особистий кабінет</label
                >
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="form.email"
                />
                <h3 class="text-neutral-700 font-bold text-2xl">Пароль</h3>
                <label class="text-neutral-500 text-sm"
                  >Для автентифікації</label
                >

                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="password"
                  v-model="form.password"
                />
                <div v-for="error in errors" :key="error.id">
                  <p class="text-sm text-red-400">{{ error }}</p>
                </div>
                <div
                  class="w-full flex flex-col justify-center text-center m-auto items-center"
                >
                  <div
                    class="items-center bg-emerald-400 p-4 rounded-xl text-white font-bold hover:bg-emerald-600 duration-300 cursor-pointer w-fit justify-center text-center gap-4 mt-2 flex"
                  >
                    <div @click="loginUser(form)">Продовжити</div>
                  </div>
                </div>
                <div
                  class="pt-4 flex text-sm flex-col justify-center text-center items-center"
                >
                  <router-link :to="{ name: 'Register' }" class="group">
                    Нема особистого кабінету?
                    <p
                      class="text-lg group transition duration-300 font-bold my-2 text-center group-hover:text-emerald-700 text-emerald-500"
                    >
                      Створити
                    </p>
                  </router-link>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";
import restProduct from "../../composables/auth";

const { loginUser, errors, userLogged } = restProduct();
const form = reactive({
  email: "",
  password: "",
});

onMounted(() => {
  userLogged();
});
</script>
