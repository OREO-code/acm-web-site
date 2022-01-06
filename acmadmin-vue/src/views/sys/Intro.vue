<template>


  <el-container>



    <el-main style="text-align: center">
      <div style="">
        <el-table
            height="660"
            max-height="100%"
            :data="introduces"
            style="width: 100%">
          <el-table-column
              align="center"
              label="ID"
              width="180">
            <template slot-scope="scope">
              <span style="text-align: center">{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column
              align="center"
              label="日期"
              column-key="date"
              width="180">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span >{{ scope.row.created }}</span>
            </template>
          </el-table-column>

          <el-table-column
              align="center"
              label="标题"
              width="180">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top">
                <p>摘要: {{ scope.row.description }}</p>
                <p>内容: {{ scope.row.content }}</p>
                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">{{ scope.row.title }}</el-tag>
                </div>
              </el-popover>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" style="width: 80px">
            <template slot="header" slot-scope="scope">
              <router-link to="/sys/intro/edit">
                <el-button type="primary">新增</el-button>
              </router-link>
            </template>
            <template slot-scope="scope">
              <el-button

                  @click="handleEdit(scope.row.id)">编辑</el-button>
              <el-button

                  type="danger"
                  @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>

      </div>
    </el-main>
  </el-container>

</template>

<script>


export default {
  name: 'Sysintro',
  data(){
    return{
      introduces :[]
    }

  },
  methods:{
    page(){
      const _this = this
      _this.$axios.get("/introduceAdmin").then(res=>{
        console.log(res.data.data)
        _this.introduces = res.data.data;
      })
    },
    handleEdit(id) {
      console.log(id);
      this.$router.push("/sys/intro/"+id+"/edit");

    },
    handleDelete(id) {
      console.log(id);
      this.$confirm('此操作将永久删除该文档, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const _this = this
        _this.$axios.get("/delIntroduce/"+id).then(res=>{
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.page();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  created() {
    this.page();
  }


}

</script>

<style>






</style>