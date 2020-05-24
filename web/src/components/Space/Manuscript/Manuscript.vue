<template>
  <div>
  <el-container>
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu>
        <el-menu-item index="2">
          <template slot="title"><router-link to="/space/manuscript/edit" style="text-decoration: none"><i class="el-icon-edit"></i>投稿</router-link></template>
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-menu"></i>内容管理</template>
          <el-menu-item-group>
            <el-menu-item index="3-1"><router-link to="/space/manuscript/management" style="text-decoration: none">稿件管理</router-link></el-menu-item>
            <el-menu-item index="3-2"><router-link to="/space/manuscript/" style="text-decoration: none">审核管理</router-link></el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="4">
          <template slot="title"><router-link to="/space/manuscript/msdata" style="text-decoration: none"><i class="el-icon-setting"></i>数据中心</router-link></template>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'Manuscript',
    data() {
      return {

      }
    }
  }
</script>

<style scoped>
  .el-container{
    padding: 0;
    margin: 10px 0 0 0;
  }

</style>
