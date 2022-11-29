<template>
  <div class="form-container">
    <form action="http://localhost:8080/login" method="get">
      <h1>Entrar</h1>
      <div class="email">
        <label for="email">email</label>
        <input type="email" id="email" name="email" required />
      </div>
      <div class="password">
        <label for="password">senha</label>
        <input type="password" id="password" name="password" required />
        <div class="password-forgot">
          <a href="">esqueci minha senha</a>
        </div>
      </div>
      <div>
        <input type="submit" value="AVANÇAR" class="button-t1"/>
        <p>
          não possui uma conta?
          <a class="signUp" href="/signup">Cadastre-se agora</a>
        </p>
      </div>
    </form>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.invalid {
  font: 500 0.85rem/1.125rem 'poppins', sans-serif;
  color: #ff5771;
  margin-bottom: 2rem;
}

.form-container {
  display: flex;
  justify-content: center;
}

form {
  background: #f5f7fa;
  height: auto;
  width: 90%;
  border-radius: 5px;
  padding: 60px;
  position: relative;
  color: #474d59;
}

h1 {
  font: 500 1.5rem/2.25rem 'poppins', sans-serif;
  color: #242833;
  margin-bottom: 2.5rem;
}

label {
  display: block;
  margin-bottom: 0.75rem;
  font: 400 0.85rem/1.125rem 'poppins', sans-serif;
  color: #636973;
}

input[type='name'],
input[type='email'],
input[type='password'] {
  position: relative;
  border: none;
  background: none;
  width: 100%;
  border-bottom: 3px solid #c0c5cc;
  font-size: 1rem;
  color: #363b47;
  transition: border-color 0.3s;
}

input:focus {
  outline: 0;
  border-bottom: 3px solid #1e90ff;
}

.password-forgot {
  margin-top: 0.75rem;
  display: flex;
  justify-content: right;
  font: 400 0.85rem/1.125rem 'roboto', sans-serif;
  color: #00478c;
}

.email {
  margin-bottom: 2rem;
}

.password {
  margin-bottom: 3rem;
}

form > div:last-of-type {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
}

p {
  display: flex;
  gap: 4px;
  flex-wrap: wrap;
  justify-content: center;
}

p,
p a {
  font: 400 1rem/1.125rem 'roboto', sans-serif;
  color: #636973;
}

p a {
  color: #1e90ff;
}

@media (max-width: 900px) {
  form {
    box-sizing: border-box;
    width: fit-content;
    padding: 40px;
  }

  @media (max-width: 430px) {
    form {
      padding: 32px;
    }

    p,
    p a {
      font: 400 0.85rem/1.125rem 'roboto', sans-serif;
    }
  }
}
</style>
