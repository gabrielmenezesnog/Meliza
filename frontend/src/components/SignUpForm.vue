<template>
  <div class="form-container">
    <form @submit.prevent="postUser">
      <h1>Faça parte</h1>
      <div class="name">
        <label for="name">nome <span>(pessoal ou empresa)</span></label>
        <input
          type="name"
          id="name"
          v-model="name"
          name="name"
          :maxlength="60"
          required
        />
      </div>
      <div class="email">
        <label for="email">email</label>
        <input
          type="email"
          id="email"
          name="email"
          v-model="email"
          :maxlength="60"
          v-on:change="validation"
          required
          v-bind:style="{ borderBottom: emailBorderBottom }"
        />
        <div class="invalid">
          <label for="email" v-if="emailStatus == false"
            >digite um email válido</label
          >
        </div>
      </div>
      <div class="password">
        <label for="password">
          senha <span>(pelo menos 8 caracteres)</span>
        </label>
        <div class="password-input">
          <input
            :type="inputType"
            id="password"
            name="password"
            v-model="password"
            :maxlength="60"
            v-on:change="validation"
            required
            v-bind:style="{ borderBottom: passwordBorderBottom }"
          />
          <div class="invalid">
            <label for="password" v-if="passwordStatus == false">
              mínimo de 8 caracteres
            </label>
          </div>
          <div @click="passwordView" class="password-visibility">
            <svg
              width="48"
              height="48"
              viewBox="0 0 48 48"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              v-if="reveal == false"
            >
              <path
                d="M24.0001 31.5c2.3667 0 4.375-.825 6.025-2.475 1.65-1.65 2.475-3.6583 2.475-6.025 0-2.3667-.825-4.375-2.475-6.025-1.65-1.65-3.6583-2.475-6.025-2.475-2.3667 0-4.375.825-6.025 2.475-1.65 1.65-2.475 3.6583-2.475 6.025 0 2.3667.825 4.375 2.475 6.025 1.65 1.65 3.6583 2.475 6.025 2.475Zm0-2.9c-1.5667 0-2.8917-.5417-3.975-1.625s-1.625-2.4083-1.625-3.975.5417-2.8917 1.625-3.975 2.4083-1.625 3.975-1.625 2.8917.5417 3.975 1.625 1.625 2.4083 1.625 3.975-.5417 2.8917-1.625 3.975-2.4083 1.625-3.975 1.625Zm0 9.4c-4.6 0-8.7917-1.25-12.575-3.75-3.78333-2.5-6.70834-5.8-8.775-9.9-.1-.1667-.175-.3667-.225-.6-.05-.2333-.075-.4833-.075-.75s.025-.5167.075-.75c.05-.2333.125-.4333.225-.6 2.06666-4.1 4.99167-7.4 8.775-9.9C15.2084 9.25 19.4001 8 24.0001 8s8.7917 1.25 12.575 3.75c3.7833 2.5 6.7083 5.8 8.775 9.9.1.1667.175.3667.225.6.05.2333.075.4833.075.75s-.025.5167-.075.75c-.05.2333-.125.4333-.225.6-2.0667 4.1-4.9917 7.4-8.775 9.9-3.7833 2.5-7.975 3.75-12.575 3.75Zm0-3c4.0333 0 7.7417-1.0917 11.125-3.275s5.9583-5.0917 7.725-8.725c-1.7667-3.6333-4.3417-6.5417-7.725-8.725C31.7418 12.0917 28.0334 11 24.0001 11s-7.7417 1.0917-11.125 3.275c-3.38334 2.1833-5.975 5.0917-7.775 8.725 1.8 3.6333 4.39166 6.5417 7.775 8.725C16.2584 33.9083 19.9668 35 24.0001 35Z"
                v-bind:fill="eyeColor"
              />
            </svg>
            <svg
              width="48"
              height="48"
              viewBox="0 0 48 48"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              v-if="reveal"
            >
              <path
                d="m31.4501 27.05-2.2-2.2c.8667-2.3667.4167-4.3333-1.35-5.9-1.7667-1.5667-3.6833-1.9667-5.75-1.2l-2.2-2.2c.5667-.3667 1.2-.6333 1.9-.8.7-.1667 1.4167-.25 2.15-.25 2.3667 0 4.375.825 6.025 2.475 1.65 1.65 2.475 3.6583 2.475 6.025 0 .7333-.0917 1.4583-.275 2.175-.1833.7167-.4417 1.3417-.775 1.875Zm6.45 6.45-2-2c1.6333-1.2 3.0583-2.5417 4.275-4.025 1.2167-1.4833 2.1083-2.975 2.675-4.475-1.6667-3.7-4.1667-6.625-7.5-8.775-3.3333-2.15-6.95-3.225-10.85-3.225-1.4 0-2.8333.1333-4.3.4-1.4667.2667-2.6167.5833-3.45.95l-2.3-2.35001c1.1667-.53333 2.6583-1 4.475-1.4 1.8167-.4 3.5917-.6 5.325-.6 4.5 0 8.65 1.23334 12.45 3.70001 3.8 2.4667 6.7 5.7833 8.7 9.95.1.1667.1667.3667.2.6.0333.2333.05.4833.05.75s-.0167.525-.05.775-.1.4583-.2.625c-.8667 1.8333-1.9333 3.5167-3.2 5.05-1.2667 1.5333-2.7 2.8833-4.3 4.05Zm1.8 10.2-7.3-7.15c-1.1667.4667-2.4833.825-3.95 1.075-1.4667.25-2.95.375-4.45.375-4.6 0-8.8167-1.2333-12.65-3.7-3.83334-2.4667-6.75-5.7833-8.75-9.95-.1-.2-.16667-.4083-.2-.625-.03334-.2167-.05-.4583-.05-.725s.025-.525.075-.775c.05-.25.10833-.4583.175-.625.7-1.5 1.59166-2.9583 2.675-4.375 1.08333-1.4167 2.35833-2.7583 3.825-4.025l-5.25-5.25001c-.3-.3-.45-.65-.45-1.05s.15-.75.45-1.05c.3-.3.65833-.45 1.075-.45.41666 0 .775.15 1.075.45l35.8 35.80001c.2667.2667.4.5917.4.975s-.1333.725-.4 1.025c-.2667.3333-.6083.5-1.025.5-.4167 0-.775-.15-1.075-.45Zm-28.55-28.4c-1.23334.9-2.425 2.0833-3.575 3.55-1.15 1.4667-1.975 2.85-2.475 4.15 1.7 3.7 4.25833 6.625 7.675 8.775C16.1918 33.925 20.0668 35 24.4001 35c1.1 0 2.1833-.0667 3.25-.2 1.0667-.1333 1.8667-.3333 2.4-.6l-3.2-3.2c-.3667.1667-.8167.2917-1.35.375-.5333.0833-1.0333.125-1.5.125-2.3333 0-4.3333-.8167-6-2.45-1.6667-1.6333-2.5-3.65-2.5-6.05 0-.5.0417-1 .125-1.5s.2083-.95.375-1.35l-4.85-4.85Z"
                v-bind:fill="eyeColor"
              />
            </svg>
          </div>
        </div>
      </div>
      <div>
        <input type="submit" value="AVANÇAR" class="button-t1" v-if="status" />
        <input
          type="submit"
          value="AVANÇAR"
          class="button-t1"
          v-if="status === false"
          disabled
        />
        <p>
          já possui uma conta?
          <a class="signUp" href="/login">Entrar</a>
        </p>
      </div>
    </form>
  </div>
</template>

<script>
import Validate from '@/services/SignUpValidation'
const validate = new Validate()

import { api } from '@/services/api'

export default {
  name: 'SignUpForm',
  data() {
    return {
      name: '',
      email: '',
      password: '',
      reveal: false,
      status: true,
      emailStatus: true,
      passwordStatus: true,
      eyeColor: '#636973',
      inputType: 'password',
      passwordBorderBottom: '',
      emailBorderBottom: '',
    }
  },
  methods: {
    passwordView() {
      let revealStatus = this.reveal

      this.reveal = !revealStatus
      this.eyeColor = '#242833'

      this.inputType = 'text'
      if (this.reveal == false) {
        this.eyeColor = '#636973'
        this.inputType = 'password'
      }
    },
    // Método para validar os campos antes de realizar
    // o post
    validation() {
      if (this.email != '') {
        const email = validate.verifyEmail(this.email)
        if (email == true) {
          this.emailStatus = true
          this.status = true
          this.emailBorderBottom = '3px solid #c0c5cc'
        } else {
          this.emailStatus = false
          this.status = false
          this.emailBorderBottom = '3px solid #ff5771'
        }
      }

      if (this.password != '') {
        const password = validate.verifyPassword(this.password)
        if (password == true) {
          this.passwordStatus = true
          this.status = true
          this.passwordBorderBottom = '3px solid #c0c5cc'
        } else {
          this.passwordStatus = false
          this.status = false
          this.passwordBorderBottom = '3px solid #ff5771'
        }
      }

      if (this.email != '' && this.password != '') {
        return true
      } else {
        return false
      }
    },
    // Método para realizar um POST de um novo usuário
    async postUser() {
      if (this.validation() == true) {
        const user = {
          name: this.name,
          email: this.email,
          password: this.password,
        }

        try {
          const res = await api.post('/signup', user)
          if (res.status == 201) {
            // redirecionando para a próxima página
            alert('Cadastrado')

            // limpando campos dos inputs
            this.name = null
            this.email = null
            this.password = null
          }

          if (res.status == 208) {
            // Informando ao usuário que algum dado
            // informado já existe no banco
            alert('Email já cadastrado')
          }
        } catch (error) {
          console.log(error)
        }
      } else console.log('errado')
    },
  },
}
</script>

<style scoped>
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
input[type='password'],
input[type='text'] {
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

span {
  font-size: 0.75rem;
  color: #7e838c;
}

.name,
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

.password-input {
  position: relative;
}

.password-visibility {
  z-index: 1000;
  background: #f5f7fa;
  width: 48px;
  height: 24px;
  position: absolute;
  right: 0;
  top: -5px;
}

.password-visibility svg {
  width: 100%;
  height: 100%;
  position: absolute;
}

.password-visibility path {
  transition: all 0.3s;
}

.password-visibility svg:hover path {
  fill: #242833;
}

.invalid label {
  margin-top: 8px;
}
.invalid label,
.invalid span {
  color: #ff5771;
  font-weight: 400;
}

@media (max-width: 900px) {
  form {
    box-sizing: border-box;
    width: 100%;
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
