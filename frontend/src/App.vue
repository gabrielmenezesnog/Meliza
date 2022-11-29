<template>
  <div id="app">
    <RouterView />
  </div>
</template>

<script>
export default {
  components: {},
};
</script>

<style>
* {
  box-sizing: border-box;
}

body,
h1,
h2,
ul,
li,
p,
dd,
dt,
dl {
  margin: 0px;
  padding: 0px;
  font-size: inherit;
  font-weight: inherit;
}

a {
  text-decoration: none;
  color: inherit;
  font-size: inherit;
  font-weight: inherit;
}

body {
  background: linear-gradient(285.1deg, #242833 0%, #0b0c12 100%);
  background-attachment: fixed;
  color: #f5f7fa;
}

img {
  display: block;
  max-width: 100%;
}

ul {
  list-style: none;
}

.container {
  box-sizing: border-box;
  max-width: 1200px;
  padding: 0px 20px;
  margin: 0 auto;
}

.button-t1 {
  box-sizing: border-box;
  background: #1e90ff;
  color: #f5f7fa;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);
  font: 500 1rem/1.5rem 'poppins', sans-serif;
  border-radius: 5px;
  border: none;
  padding: 12px 24px;
  width: 100%;
  text-align: center;
  cursor: pointer;
  transition: 0.3s;
}

.button-t1:hover {
  background: #0079ef;
}

.button-t1:disabled {
  background: #81c1ff;
  cursor: alias;
}

.welcome {
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  position: relative;
  top: 50vh;
  transform: translateY(-50%);
}

@media (max-width: 900px) {
  .welcome {
    grid-template-columns: 1fr;
    gap: 2.5rem;
    margin-bottom: 120px;
  }
}
</style>
