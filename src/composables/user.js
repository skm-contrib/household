import { ref } from "vue";
import axios from "axios";

export default function restUser() {
  const user = ref([]);
  const users = ref([]);

  const errors = ref({});

  const getUser = async () => {
    try {
      const response = await axios.get(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/users",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
      console.log(response);
      user.value = response.data;
    } catch (er) {
      console.log(er);
    }
  };

  const getUsers = async () => {
    const response = await axios.get(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/users"
    );
    users.value = response.data.data;
  };

  const updateUser = async (id) => {
    try {
      await axios.put(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/users/" +
          id,
        user.value
      );
    } catch (error) {
      errors.value = error.response.data.errors;
    }
  };

  const createUser = async (data) => {
    try {
      await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/users",
        data
      );
    } catch (error) {
      if (error.response.status === 422) {
        errors.value = error.response.data.errors;
      }
    }
  };

  const deleteUser = async (id) => {
    if (!window.confirm("Видалити користувача?")) {
      return;
    }
    await axios.delete(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/users/" +
        id
    );
  };

  return {
    user,
    users,
    errors,
    getUser,
    getUsers,
    createUser,
    updateUser,
    deleteUser,
  };
}
