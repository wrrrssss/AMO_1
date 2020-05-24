<template>
  <div>
    <HomeHead></HomeHead>
    <el-form :inline="true">
    <el-input placeholder="请输入内容" v-model="content" style="width: 500px">
      <el-select v-model="select" slot="prepend" placeholder="请选择" style="width: 150px">
        <el-option label="标题搜索" value="1"></el-option>
        <el-option label="作者搜索" value="2"></el-option>
      </el-select>
      <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
    </el-input>
    </el-form>
    <div class="content_box">
      <el-row :gutter="10">
        <!--分页-->
        <el-col :span="6">
          <h2 style="margin-top:4px">搜索结果</h2>
        </el-col>
        <el-col>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[8, 16, 24, 32]"
            :page-size="perPage"
            layout="total, sizes, prev, pager, next"
            :total="card_list.length">
          </el-pagination>
        </el-col>
        <!--循环显示-->
        <el-col :sm="12" :md="6" v-for="(card, index) in showAppointList" :key="card.id">
          <picture-card :manuscript="card" ></picture-card>
        </el-col>
      </el-row>
      <el-col :span="4">&nbsp;</el-col>
    </div>
  </div>
</template>

<script>
  import PictureCard from './picture-card.vue'
  import axios from 'axios'
  import HomeHead from "../Head/HomeHead";
  export default {
    name: '',
    data() {
      return {
        card_list: [],
        currentPage: 1,
        perPage: 8,
        content: '',
        select: ''
      }
    },
    components: {
      HomeHead,
      'picture-card': PictureCard
    },
    created() {
    },
    computed: {
      showAppointList() {
        return this.card_list.slice((this.currentPage - 1) * this.perPage, this.currentPage * this.perPage);
      }
    },
    methods: {
      search() {
        if (this.select == 1){
          axios.get('http://localhost:8088/recommend/searchByTitle',{
            params: {
              demo: this.content
            }
          }).then(response => {
            let array = response.data
            for (let i = 0;i < array.length; i++){
              this.card_list.push(array[i])
            }

            console.log(this.card_list)
          })
        }
        else{
          axios.get('http://localhost:8088/recommend/getManuscriptByUser',{
            params: {
              user_id: this.content
            }
          }).then(response => {
            let array = response.data
            for (let i = 0;i < array.length; i++){
              this.card_list.push(array[i])
            }
            console.log(this.card_list)
          })
        }
      },
      handleSizeChange(val) {
        this.perPage = val
      },
      handleCurrentChange(val) {
        this.currentPage = val
      }
    }
  }
</script>

<style scoped>
  .content_box{
    margin-top: 20px;
  }
</style>
