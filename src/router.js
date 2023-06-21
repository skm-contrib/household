import { createRouter, createWebHistory } from "vue-router";

import HomePageComponent from "./components/pages/HomePageComponent.vue";
import ProductPage from "./components/pages/ProductPage.vue";
import ProfilePage from "./components/pages/ProfilePage.vue";
import ProductsPage from "./components/pages/ProductsPage.vue";
import PayoutPage from "./components/pages/PayoutPage.vue";

import LoginPage from "./components/authpages/LoginPage.vue";
import RegisterPage from "./components/authpages/RegisterPage.vue";

import UserEdit from "./components/editable/UserEdit.vue";

import AdminComponent from "./components/admin/AdminComponent.vue";
import EditProduct from "./components/admin/EditProduct.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomePageComponent,
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminComponent,
  },
  {
    path: "/payout",
    name: "Payout",
    component: PayoutPage,
  },
  {
    path: "/products",
    name: "Products",
    component: ProductsPage,
    props: true,
  },
  {
    path: "/users/:id?/edit",
    name: "UserEdit",
    component: UserEdit,
    props: true,
  },
  {
    path: "/product/:id?",
    name: "ProductPage",
    component: ProductPage,
    props: true,
  },
  {
    path: "/products/:id?/edit",
    name: "EditProduct",
    component: EditProduct,
    props: true,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginPage,
  },
  {
    path: "/register",
    name: "Register",
    component: RegisterPage,
  },
  {
    path: "/profile",
    name: "Profile",
    component: ProfilePage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
