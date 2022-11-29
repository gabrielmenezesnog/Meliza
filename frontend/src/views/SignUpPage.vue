<template>
  <section aria-label="Login">
    <div class="container grid">
      <WelcomeLogo />
      <SignUpForm />
    </div>
  </section>
</template>

<script>
import WelcomeLogo from '@/components/WelcomeLogo.vue';
import SignUpForm from '@/components/SignUpForm.vue';
export default {
  components: { WelcomeLogo, SignUpForm },
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
