import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "",
			component: () => import("../views/AccountCreateView.vue"),
			alias: ["/", "/index.html", "/creer-compte"],
		},
		{
			path: "/authentificate",
			name: "AccountAuthentificate",
			component: () => import("../views/AccountAuthentificateView.vue"),
		},
		{
			path: "/:pathMatch(.*)*",
			name: "NotFound",
			component: () => import("../views/NotFoundView.vue"),
		},
	],
});

export default router;
