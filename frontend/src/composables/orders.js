import { ref, reactive } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default function restOrder() {
  const order = ref([]);
  const orders = ref([]);
  const router = useRouter();

  const errors = ref({});

  const getOrder = async (id) => {
    const response = await axios.get(
      "http://localhost:8001/api/v1/orders/" + id
    );
    order.value = response.data.data;
  };

  const getOrders = async () => {
    const response = await axios.get("http://localhost:8001/api/v1/orders/all");
    orders.value = response.data;
    console.log(orders.value);
  };

  const getUserOrders = async () => {
    try {
      const response = await axios.get("http://localhost:8001/api/v1/orders", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      orders.value = response.data;
    } catch (err) {
      console.log(err);
    }
  };

  const getOrdersByStatus = async (status_id) => {
    const response = await axios.get(
      "http://localhost:8001/api/v1/orders?status=" + status_id
    );
    orders.value = response.data.data;
  };

  const updateOrderStatus = async (id, status_id) => {
    try {
      await axios.put(
        "http://localhost:8001/api/v1/orders/" + id + "/status=" + status_id
      );
    } catch (error) {
      console.log(error);
    }
    router.go();
  };

  const createOrder = async (data) => {
    const token = localStorage.getItem("token");

    const payment = reactive({
      cardNumber: data.cardNumber,
      expirationDate: data.expirationDate,
      cvv: data.cvv,
    });

    const order = reactive({
      products: data.productsToBuy,
      payment: payment,
      jwt: token,
    });

    try {
      const response = await axios.post(
        "http://localhost:8001/api/v1/orders",
        order
      );
      console.log(response.data);
    } catch (error) {
      if (error.response.status === 502) {
        errors.value = error.response;
      }
    }
    localStorage.removeItem("userBasket");
    router.push({ name: "Profile" });
  };

  const deleteOrder = async (id) => {
    if (!window.confirm("Видалити замовлення?")) {
      return;
    }
    await axios.delete("http://localhost:8001/api/v1/orders/" + id);
  };

  return {
    order,
    orders,
    getOrder,
    getOrders,
    getOrdersByStatus,
    getUserOrders,
    createOrder,
    deleteOrder,
    updateOrderStatus,
  };
}
