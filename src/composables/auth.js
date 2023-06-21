import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useRoute } from "vue-router";

export default function restAuth() {
  const router = useRouter();
  const route = useRoute();
  const errors = ref([]);

  const loginUser = async (data) => {
    try {
      const response = await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/auth/authenticate",
        data
      );
      let token = response.data.token;

      localStorage.setItem("token", token);

      router.go();

      if (response.status == 200) {
        router.push({ name: "Profile" });
      }
    } catch (err) {
      console.log(err.response);
      errors.value = err.response.data.errors;
    }
  };

  const userLogged = async () => {
    if ("token" in localStorage) {
      const token = localStorage.getItem("token");
      console.log(token);
      router.push({ name: "Profile" });
    } else {
      if (route.name === "Login") {
        router.push({ name: "Login" });
      } else {
        router.push({ name: "Register" });
      }
    }
  };

  const logout = async () => {
    localStorage.clear();
    router.push({ name: "Login" });
  };

  const signUpUser = async (data) => {
    try {
      data.phone += "";
      const response = await axios.post(
        "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/auth/signup",
        data
      );

      let token = response.data.token;

      localStorage.setItem("token", token);

      if (response.status == 200) {
        router.push({ name: "Profile" });
      }
    } catch (err) {
      errors.value = err.response.data.errors;
    }
  };

  const logoutUser = async () => {
    await axios.post(
      "https://householdchemicalstore-6a2d633af2a8.herokuapp.com/api/v1/auth/logout"
    );
  };

  return {
    loginUser,
    signUpUser,
    logoutUser,
    logout,
    userLogged,
    errors,
  };
}
