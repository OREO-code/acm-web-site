<template>

  <div >
    <el-timeline>
      <el-timeline-item v-for="introduce in introduces" :timestamp="introduce.created" placement="top" type="primary">
        <router-link :to="{name: 'IntroduceDetail',params:{introduceId:introduce.id}}" style="text-decoration: none">
          <el-card style="text-align: center;background: #58B7FF;">
            <h4 >{{introduce.title}}</h4>
            <p >{{introduce.description}}</p>
          </el-card>
        </router-link>
      </el-timeline-item>
      <el-pagination class="mpage"
          background
          layout="prev, pager, next"
                     :current-page="currentPage"
                     :page-size="pageSize"
                     :total="total"
                     @current-change=page
          >
      </el-pagination>
    </el-timeline>
  </div>
</template>

<script>


export default {
  name: 'Introduce',
  data(){
    return{
      introduces :{},
      currentPage:1,
      total:0,
      pageSize:5
    }
  },
  methods:{
    page(currentPage){
      const _this = this
      _this.$axios.get("http://101.43.16.42:8082/introduces?currentPage="+currentPage).then(res=>{
        _this.introduces = res.data.data.records;
        console.log(res)
        _this.introduces = res.data.data.records;
        _this.currentPage = res.data.data.current;
        _this.total = res.data.data.total;
        _this.pageSize = res.data.data.size;
      })
    }
  },
  created() {
    this.page(1);
  }

}

</script>

<style>
.el-timeline-item__timestamp{
  text-align: left !important;
  color: #909399;
  line-height: 2;
  font-size: 15px !important;
}
.el-card__body{
  padding: unset !important;
}

</style>