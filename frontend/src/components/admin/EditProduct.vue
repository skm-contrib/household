<template>
  <h1 class="text-4xl my-12 font-bold text-neutral-600 text-center">
    Редагування продукту
  </h1>

  <div class="flex flex-col m-auto justify-center w-2/3 mb-32">
    <div
      class="w-2/3 gap-4 border-2 border-white bg-white rounded-3xl m-auto flex flex-col bg-opacity-70 justify-evenly backdrop-filter backdrop-blur-lg"
    >
      <div class="grow flex flex-col text-center justify-center">
        <div class="text-left">
          <form @submit.prevent="">
            <div class="flex flex-col rounded-2xl">
              <div class="flex flex-col m-4 p-4 gap-2 rounded-2xl">
                <h3 class="text-neutral-700 font-bold text-2xl">
                  Назва продукту
                </h3>
                <label class="text-neutral-500 text-sm"
                  >Для зручного орієнтування</label
                >
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="product.name"
                />
                <h3 class="text-neutral-700 font-bold text-2xl">Опис</h3>
                <label class="text-neutral-500 text-sm"
                  >Короткий лаконічний опис</label
                >

                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="text"
                  v-model="product.description"
                />

                <h3 class="text-neutral-700 font-bold text-2xl">Ціна</h3>
                <label class="text-neutral-500 text-sm"
                  >Зміна ціни продукту</label
                >

                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="number"
                  v-model="product.price"
                />
                <div v-for="review in product.reviews" :key="review.id">
                  <div
                    v-if="review.text != null"
                    class="bg-emerald-600 gap-4 rounded-xl flex flex-row p-4 border-emerald-800 border-2 text-white"
                  >
                    <div class="">
                      <div class="flex flex-col text-center">
                        <p class="font-bold text-xl">
                          {{ review.reviewOwner.name }}
                        </p>
                        <div>
                          {{ review.reviewOwner.surname }}
                          {{ review.reviewOwner.patronymic }}
                        </div>
                      </div>
                      {{ review.timeOfReview }}
                    </div>
                    <div>
                      <input
                        type="text"
                        v-model="review.text"
                        class="bg-transparent"
                      />
                    </div>

                    <div
                      class="flex flex-col items-center justify-around gap-4 m-4 border-l-4 pl-4 border-neutral-700"
                    >
                      <div
                        @click="deleteReviewInComponent(review.id)"
                        class="routerlink cursor-pointer bg-red-500 border-red-500 hover:border-red-500 hover:text-white"
                      >
                        Видалити
                      </div>
                    </div>
                    <div></div>
                  </div>
                </div>
                <h3 class="text-neutral-700 font-bold text-2xl">Фото</h3>
                <label class="text-neutral-500 text-sm"
                  >Вдалі фото продукту
                </label>
                <input
                  class="w-full px-4 py-4 border-2 border-emerald-500 rounded-xl shadow-2xl transition-colors duration-300 ease-in-out focus:outline-none focus:border-transparent"
                  type="file"
                  accept="image/png, image/jpg, image/jpeg, image/webp"
                  @change="handleFileUpload"
                />
                <div class="flex justify-center">
                  <img
                    class="m-4 h-32 w-32 rounded-3xl object-cover"
                    :src="
                      'http://localhost:8001/api/v1/images/' + product.imagePath
                    "
                    alt=""
                  />
                </div>

                <div class="items-center gap-4 mt-12 flex justify-center">
                  <button type="submit">
                    <div
                      @click="updateProduct(product.id)"
                      class="routerlink m-4 p-4 bg-emerald-600 border-emerald-600 hover:border-emerald-600 hover:text-emerald-600 font-bold"
                    >
                      Оновити
                    </div>
                  </button>
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
import { useRoute } from "vue-router";
import restCategory from "../../composables/category";
import restProduct from "../../composables/product";
import restReview from "@/composables/review";

const router = useRoute();

const { getCategories } = restCategory();
const { getProduct, updateProduct, product } = restProduct();
const { deleteReview } = restReview();

const handleFileUpload = async (e) => {
  const file = e.target.files[0];
  product.value.image = file;
};

const deleteReviewInComponent = (reviewId) => {
  if (product.value.reviews) {
    const index = product.value.reviews.findIndex(
      (review) => review.id === reviewId
    );

    if (index !== -1) {
      product.value.reviews.splice(index, 1);
    }
  } else {
    console.log("no");
  }

  deleteReview(reviewId);
};

onMounted(() => {
  getProduct(router.params.id);
  getCategories();
});
</script>
