<template>
  <div class="flex flex-col m-auto justify-center w-2/3 pb-48">
    <div
      class="sm:w-1/3 w-full gap-4 border-2 border-white bg-white rounded-3xl sm:m-auto flex flex-col bg-opacity-70 justify-evenly backdrop-filter backdrop-blur-lg"
    >
      <h1
        class="sm:text-4xl text-2xl mt-12 font-bold text-neutral-600 text-center"
      >
        Редагування профілю
      </h1>
      <div class="grow flex flex-col text-center justify-center">
        <div class="text-left">
          <form @submit.prevent="">
            <div class="flex flex-col rounded-2xl">
              <div class="flex flex-col m-4 p-4 gap-2 rounded-2xl">
                <h3 class="text-neutral-700 font-bold text-2xl">Ім'я</h3>
                <label class="text-neutral-500 text-sm"
                  >Введіть ваше ім'я</label
                >
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="user.name"
                />
                <h3 class="text-neutral-700 font-bold text-2xl">Прізвище</h3>
                <label class="text-neutral-500 text-sm">Ваще прізвище</label>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="user.surname"
                />
                <h3 class="text-neutral-700 font-bold text-2xl">По-Батькові</h3>
                <label class="text-neutral-500 text-sm"
                  >Введіть ваше ім'я</label
                >
                {{ user }}
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="user.patronymic"
                />

                <h3 class="text-neutral-700 font-bold text-2xl">
                  Електронна пошта
                </h3>
                <label class="text-neutral-500 text-sm"
                  >Для доступу в особистий кабінет</label
                >
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="user.email"
                />

                <h3 class="text-neutral-700 font-bold text-2xl">
                  Номер телефону
                </h3>
                <label class="text-neutral-500 text-sm"
                  >Для певних авторизаційних дій
                </label>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="number"
                  v-model="user.phone"
                />

                <h3 class="text-neutral-700 font-bold text-2xl">Пароль</h3>
                <label class="text-neutral-500 text-sm">Ваш пароль</label>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="user.password"
                />

                <div v-for="error in errors" :key="error.id">
                  <p class="text-sm text-red-400">{{ error }}</p>
                </div>
                <div class="items-center gap-4 mt-2 flex justify-center">
                  <button type="submit">
                    <div
                      @click="updateUser(user.id)"
                      class="routerlink bg-emerald-600 border-emerald-600 hover:text-emerald-600 font-bold"
                    >
                      Оновити дані
                    </div>
                  </button>
                </div>
                <div
                  class="pt-4 flex text-sm flex-col justify-center text-center items-center"
                >
                  <router-link :to="{ name: 'Profile' }" class="group">
                    Не зберігати зміни?
                    <p
                      class="text-lg group transition duration-300 font-bold my-2 text-center group-hover:text-emerald-700 text-emerald-500"
                    >
                      Повернутись у профіль
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
import { onMounted } from "vue";
import restUser from "@/composables/user";

const { getUser, errors, updateUser, user } = restUser();

onMounted(() => {
  getUser();
});
</script>
