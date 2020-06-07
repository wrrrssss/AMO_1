<template>
  <div>
    <SpaceHead></SpaceHead>
    <OtherSpaceHead></OtherSpaceHead>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
    import OtherSpaceHead from "../Head/OtherSpaceHead";
    import SpaceHead from "../Head/SpaceHead";
    export default {
        name: "OtherSpace",
        components: {SpaceHead, OtherSpaceHead},
    }
</script>

<style scoped>
  .content{
    margin: 5px 100px 0 100px;
  }
</style>
