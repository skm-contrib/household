import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

export default function restOrderStatus() {
  const order_status = ref([]);
  const order_statuses = ref([]);
  const router = useRouter();

  const errors = ref({});

  const getOrderStatus = async (id) => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/order_statuses/" +
        id
    );
    order_status.value = response.data.data;
  };

  const getOrderStatuses = async () => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/order_statuses"
    );
    order_statuses.value = response.data;
  };

  const updateOrderStatus = async (status) => {
    try {
      const response = await axios.put(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/order_statuses/" +
          status.id,
        status
      );
      console.log(response);
    } catch (error) {
      console.log(error);
    }
    router.go();
  };

  const createOrderStatus = async (data) => {
    try {
      await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/order_statuses",
        data
      );
    } catch (error) {
      if (error.response.status === 422) {
        errors.value = error.response.data.errors;
      }
    }
    router.go();
  };

  const deleteOrderStatus = async (id) => {
    if (!window.confirm("Видалити статус замовлення?")) {
      return;
    }
    await axios.delete(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/order_statuses/" +
        id
    );
    router.go();
  };

  return {
    order_status,
    order_statuses,
    getOrderStatus,
    getOrderStatuses,
    createOrderStatus,
    deleteOrderStatus,
    updateOrderStatus,
  };
}
