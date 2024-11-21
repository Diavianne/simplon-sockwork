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
      const response = await fetch("http://localhost:8080/accounts", options);
      if (response.ok) {
        alert("Compte créé avec succès");
      } else {
        console.error("Dev is a failure!");
      }
    },
  },
};
</script>

<template>
  <h1>Créer un compte</h1>
  <form @submit.prevent="submit" novalidate>
    <div>
      <label for="username">Nom d'utilisateur</label>
      <input
        type="text"
        name="username"
        id="username"
        v-model="inputs.username"
      />
    </div>
    <div>
      <label for="password">Mot de passe</label>
      <input
        type="password"
        name="password"
        id="password"
        v-model="inputs.password"
      />
    </div>
    <div class="btn">
      <button type="submit">Créer un compte</button>
    </div>
  </form>
</template>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 20%;
}
h1 {
  color: blue;
}
label::after {
  content: "*";
  color: red;
}
label {
  display: block;
}
input {
  width: 100%;
  margin-top: 0.5rem;
}
button {
  width: 50%;
}
.btn {
  display: flex;
  justify-content: center;
}
</style>
