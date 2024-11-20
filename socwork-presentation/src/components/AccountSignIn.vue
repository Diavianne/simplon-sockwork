<script>
export default {
  data() {
    return {
      inputs: { username: "", password: "" },
    };
  },
  methods: {
    async submit() {
      const options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.inputs),
      };
      const response = await fetch("http://localhost:8080/signin", options);
      if (response.ok) {
        alert("Sign in successful");
      } else {
        console.error("Sign in failed");
      }
    },
  },
};
</script>

<template>
  <h1>Se connecter</h1>
  <form @submit.prevent="submit" novalidate>
    <label for="username">Nom d'utilisateur</label>
    <input
      type="text"
      name="username"
      id="username"
      v-model="inputs.username"
    />
    <label for="password">Password</label>
    <input
      type="password"
      name="password"
      id="password"
      v-model="inputs.password"
    />
    <div>
      <button type="submit">Sign In</button>
    </div>
  </form>
</template>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 20%;
  justify-content: center;
}
h1 {
  color: blue;
}
label::after {
  content: "*";
  color: red;
}
button {
  width: 50%;
}
div {
  text-align: center;
}
</style>
