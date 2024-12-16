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
      const response = await fetch(
        "http://localhost:8080/accounts/authentificate",
        options
      );
      if (response.ok) {
        const data = await response.text();
        alert("authentification succes");
        console.log(data);
      } else if (response.status == 401) {
        alert("Bad Credentials");
        const data = await response.text();

        console.error(data);
      }
    },
  },
};
</script>

<template>
  <h1>Connectez votre compte</h1>
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
      <button type="submit">Se Connecter</button>
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
  color: rgb(203, 8, 134);
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
