<template>
  <el-container>
    <el-container>
      <el-main style="padding: 0;height: 660px;">
        <el-table
            height="660"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            max-height="100%"
            :data="fileList"
            style="width: 100%;line-height: normal;"
            highlight-current-row="true">
          <el-table-column
              label="ID"
              width="150%"
              prop="id">
          </el-table-column>
          <el-table-column
              label="文件名"
              prop="fileName">
          </el-table-column>
          <el-table-column
              label="文件地址"
              prop="fileUrl">
          </el-table-column>
          <el-table-column
              label="上传时间"
              prop="created">
          </el-table-column>
          <el-table-column
              align="right">
            <template slot="header" slot-scope="scope">
              <el-upload
                  class="upload-demo"
                  action="http://101.43.16.42:8082/upload"
                  :headers="headers"
                  :show-file-list="false"
                  multiple
                  accept=".pdf,.mp4"
                  :on-exceed="handleExceed"
                  :on-success="getFileList"
                  :on-error="myError"
                  :file-list="fileList">
                <el-button size="small" type="primary" style="line-height: normal">点击上传</el-button>
              </el-upload>
            </template>
            <template slot-scope="scope">
              <el-button

                  type="danger"
                  @click="open(scope.row.id)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: "pdfFile",
  data() {
    return {
      headers: {
        "Authorization": localStorage.getItem("token")
      },
      fileList: [],
    };
  },
  methods: {
    myError(){
      this.$message.error('上传失败');
    },

    handleDelete(id) {
      const _this = this
      this.$axios
          .get("/delFile/" + id)
          .then((res) => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            _this.getFileList()
          })

    },
    getFileList() {
      this.$axios
          .get('/mp4List')
          .then((res) => {
            this.fileList = res.data.data.fileList
          })
          .catch((error) => {
            this.$message.error("查询失败");
          })
    },

    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },


    open(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(id);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  computed: {

  },
  mounted() {
    this.getFileList()
  }
}

</script>

<style scoped>


</style>