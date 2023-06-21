import { ref, reactive } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default function restProduct() {
  const router = useRouter();
  const product = ref([]);
  const products = ref([]);
  const productsCount = ref([]);

  const errors = ref({});

  const getProducts = async () => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/all"
    );
    products.value = response.data;
  };
  const getProductsCount = async () => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/count"
    );
    productsCount.value = response.data;
  };

  const getProductsByPageNumber = async (page_number, products_count) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products?page=" +
        page_number +
        "&count=" +
        products_count
    );
    products.value = response.data;
  };

  const getProductsByCategoryId = async (category_id) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products?category=" +
        category_id
    );
    console.log(category_id);
    products.value = response.data.data;
  };

  const getProductsByPageNumberAndCategoryId = async (
    page_number,
    category_id
  ) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/all?category=" +
        category_id +
        "&page=" +
        page_number
    );
    products.value = response.data.data;
  };

  const getProductsByNameAndPageNumberAndCategoryId = async (
    name,
    page_number,
    category_id,
    count
  ) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products?search=" +
        name +
        "&category_id=" +
        category_id +
        "&page=" +
        page_number +
        "&count=" +
        count
    );
    products.value = response.data;
    console.log(products.value);
  };

  const getProductsByNameAndPageNumber = async (name, page_number) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/all?search=" +
        name +
        "&page=" +
        page_number
    );
    products.value = response.data.data;
  };

  const getProduct = async (id) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/" +
        id
    );
    product.value = response.data;
  };

  const updateProduct = async (id) => {
    try {
      const formData = new FormData();
      formData.append("image", product.value.image);
      const response = await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/images/upload",
        formData
      );

      const form = reactive({
        name: product.value.name,
        description: product.value.description,
        categoryId: product.value.category.id,
        imagePath: response.data,
        price: product.value.price,
        disabled: false,
      });
      await axios.put(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/" +
          id,
        form
      );
    } catch (error) {
      if (error.response.status === 422) {
        errors.value = error.response.data.errors;
      }
    }
    router.go();
  };

  const createProduct = async (data) => {
    try {
      const formData = new FormData();
      formData.append("image", data.image);
      const response = await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/images/upload",
        formData
      );
      console.log(response.data);
      console.log(data.category);

      const form = reactive({
        name: data.name,
        description: data.description,
        categoryId: data.category_id,
        imagePath: response.data,
        price: data.price,
        disabled: false,
      });

      await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products",
        form
      );
    } catch (err) {
      errors.value = err.response.data.errors;
    }
    router.go();
  };

  const deleteProduct = async (id) => {
    if (!window.confirm("Видалити продукт?")) {
      return;
    }
    await axios.delete(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/products/" +
        id
    );
    router.go();
  };

  return {
    product,
    products,
    productsCount,
    errors,
    getProduct,
    getProducts,
    getProductsCount,
    getProductsByCategoryId,
    getProductsByNameAndPageNumber,
    getProductsByNameAndPageNumberAndCategoryId,
    getProductsByPageNumber,
    getProductsByPageNumberAndCategoryId,
    updateProduct,
    createProduct,
    deleteProduct,
  };
}
