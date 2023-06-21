import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default function restCategory() {
  const category = ref([]);
  const categories = ref([]);
  const router = useRouter();

  const errors = ref({});

  const getCategories = async () => {
    try {
      const response = await axios.get(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/categories"
      );
      categories.value = response.data;
      console.log(response);
    } catch (e) {
      console.log(e);
    }
  };

  const getCategory = async (id) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/categories/" +
        id
    );
    category.value = response.data.data;
  };

  const updateCategory = async (cat) => {
    try {
      console.log(cat);
      await axios.put(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/categories/" +
          cat.id,
        cat
      );
    } catch (error) {
      if (error.response.status === 422) {
        errors.value = error.response.data.errors;
      }
    }
    router.go();
  };

  const createCategory = async (data) => {
    try {
      await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/categories",
        data
      );
    } catch (error) {
      console.log(error);
      if (error.response.status === 422) {
        errors.value = error.response.data.errors;
      }
    }
    router.go();
  };

  const deleteCategory = async (id) => {
    if (!window.confirm("Видалити категорію?")) {
      return;
    }
    await axios.delete(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/categories/" +
        id
    );
    router.go();
  };

  return {
    category,
    categories,
    getCategory,
    getCategories,
    createCategory,
    deleteCategory,
    updateCategory,
  };
}
