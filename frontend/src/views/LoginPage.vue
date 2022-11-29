<template>
  <div class="container grid" key="login">
    <WelcomeLogo />
    <LoginForm />
  </div>
</template>

<script>
import WelcomeLogo from '@/components/WelcomeLogo.vue';
import LoginForm from '@/components/LoginForm.vue';
export default {
  components: { WelcomeLogo, LoginForm },
};
</script>

<style scoped>
.grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  position: relative;
  top: 50vh;
  transform: translateY(-50%);
}

@media (max-width: 900px) {
  .grid {
    grid-template-columns: 1fr;
    gap: 2.5rem;
    margin-bottom: 120px;
  }
}
</style>
