<template>
  <div>
    <div>
      <SpaceHead></SpaceHead>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import SpaceHead from '../Head/SpaceHead'
  export default {
    name: 'Space',
    components: {SpaceHead}
  }
</script>

<style scoped>

</style>
