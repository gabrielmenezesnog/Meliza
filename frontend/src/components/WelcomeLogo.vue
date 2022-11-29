<template>
  <div class="logo-container">
    <a href="/homePage" class="logo">
      <svg
        width="38"
        height="54"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="m11.714 18 12.143 18L36 18h-9.714L14.143 36 2 18h9.714Z"
          stroke="#1E90FF"
          stroke-width="2"
        />
      </svg>
      <h1>meliza</h1>
    </a>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.logo-container {
  display: flex;
  justify-content: center;
}
.logo {
  width: fit-content;
  padding: 6px 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  font: 400 2rem/3.375rem 'poppins', sans-serif;
  color: #f5f7fa;
}
</style>
