<template>
  <div >
    <el-container >
      <el-main style="display: flex;justify-content: center;align-items: center;padding: 0rem !important;">
        <el-card class="box-card" style="width: 83% !important;">
          <template>
            <div class="headbar">
              轮播图效果Demo
            </div>
            <el-carousel :interval="4000" type="card" height="200px">
              <el-carousel-item v-for="(item,index) in showImageList" :key="index">
                <el-image :src="item.url" style="position:absolute;top:0;bottom:0;left:0;right:0;width:100%;margin:auto;" fit="contain"></el-image>
              </el-carousel-item>
            </el-carousel>
          </template>
        </el-card>
      </el-main>
    </el-container>
    <div style="display: flex;justify-content: center;align-items: center;width: 100%;margin-top: 3%;">
      <div class="imgshow" >
        <div v-for="item in showImageList" class="imgbtn">
          <div >
            <el-image :src="item.url"  fit="contain"></el-image>
          </div>
          <div class="deletebtn">
            {{item.id}}
            <el-button type="danger" icon="el-icon-delete" circle @click="getvalue(item.id)" ></el-button>
          </div>
        </div>
        <div class="imgbtn">
          <el-upload
              ref="upload"
              action="http://101.43.16.42:8082/addRotation"
              :before-upload="beforeAvatarUpload"
              :headers="myHeaders"

              :limit="1"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-success="GetUrl">
            <i class="el-icon-plus"></i>
          </el-upload>
        </div>
        <!-- <el-upload
           class="avatar-uploader"
           action="http://101.43.16.42:8082/addRotation"
         :on-error="uploaderr"
         :headers="headers"
           :show-file-list="false"
           :on-success="handleAvatarSuccess"
           :before-upload="beforeAvatarUpload">
           <img v-if="imageUrl" :src="imageUrl" class="avatar">
           <i v-else class="el-icon-plus avatar-uploader-icon"></i>
         </el-upload> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: '',
      showImageList:[],
      sum:'',
      imgHeight: '',
      // 图片父容器高度
      bannerHeight :1000,
      // 浏览器宽度
      screenWidth :0,
      token:'',
      fileList: [],
      deleteid:{
        id:''
      },
      uploaddata:{

      },
      myHeaders:{
        'Authorization' : localStorage.getItem("token")
      },
    };
  },

  created() {
    var _that=this

    this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
      _that.showImageList=response.data.data.urlList
      console.log(response);
    }).catch((response)=>{
      console.log('err')
      console.log(response);
    })
  },
  mounted() {
    // 首次加载时,需要调用一次
    this.screenWidth =  window.innerWidth;
    this.setSize();
    // 窗口大小发生改变时,调用一次
    window.onresize = () =>{
      this.screenWidth =  window.innerWidth;
      this.setSize();
    }
  },
  methods:{
    getvalue(value){
      var _that=this
      console.log(value)
      this.deleteid.id=value
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(JSON.stringify(this.deleteid))
        this.$axios.post('/delRotation',JSON.stringify(this.deleteid)).then((response)=>{
          this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
            _that.showImageList=response.data.data.urlList
            console.log(response);
          }).catch((response)=>{
            console.log('err')
            console.log(response);
          })
          console.log(response);
        }).catch((response)=>{
          console.log('err')
          console.log(response);
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    getInfo(){
      console.log('执行')
      this.$axios.get('http://101.43.16.42:8082/notice')
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
    },
    canvasbg(){

    },
    setSize:function () {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = 400 / 1920 * this.screenWidth;
    },
    linebackground:function(){

    },
    addimg:function(){
      console.log('添加图片')
      // this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
      // 		_that.showImageList=response.data.data.urlList
      //         console.log(response);
      //       }).catch((response)=>{
      // 		console.log('err')
      //         console.log(response);
      //       })
    },

    delete(value){
      var _that=this
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('http://101.43.16.42:8082/rotation',{params:{id:value}}).then((response)=>{
          _that.showImageList=response.data.data.urlList
          console.log(response);
        }).catch((response)=>{
          console.log('err')
          console.log(response);
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    handleAvatarSuccess(res, file, fileList) {
      var _that=this
      console.log('上传成功')
      console.log(res);
      console.log(file)
      this.$message({
        showClose: true,
        message: res
      });
      this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
        _that.showImageList=response.data.data.urlList
        console.log(response);
      }).catch((response)=>{
        console.log('err')
        console.log(response);
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isSVG = file.type === 'image/svg';
      const isLt100M = file.size / 1024 / 1024 < 100;
      let extension = file.name.substring(file.name.lastIndexOf('.')+1)
      if ((extension!='jpg')&&(extension!='png')&&(extension!='svg')) {
        this.$message.error('上传图片只能是jpg或png或svg格式!');
      }
      if (!isLt100M) {
        this.$message.error('上传头像大小不能超过 100MB!');
      }
      return (isJPG||isPNG||isSVG) && isLt100M;
    },
    uploaderr(err, file, fileList){
      console.log(err)
      this.$message.error('上传失败:'+err);
    },
    //1
    GetUrl(response, file, fileList){
      // this.ruleForm.url = response.data
      var _that=this
      this.$refs.upload.clearFiles();
      this.$axios.get('http://101.43.16.42:8082/rotation').then((response)=>{
        _that.showImageList=response.data.data.urlList
        console.log(response);
      }).catch((response)=>{
        console.log('err')
        console.log(response);
      })

    },
    handleRemove(file, fileList) {
      console.log(file, fileList);

    },
    handlePictureCardPreview(file) {
      console.log(file.url)
      //this.dialogImageUrl = file.url;
      this.dialogVisibleImage = true;
    },



  }
}
</script>
<style scope>
.headbar{
  width: 100%;
  /* 		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
  margin-top: 1%;
  height: 10%;
  padding-top: 2%;
  padding-bottom: 2%;
  margin-bottom: 3%;
  font-size: 1.5625rem;
  text-align: center;
  font-weight: bold;
  border-bottom: 0.0625rem solid #409EFF;
}
.operate{
  width: 80%;
  margin-left: 10%;
  height: auto;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin-top: 3%;
  margin-bottom: 3%;
  align-items: center;
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: center;
  padding: 1.875rem;
}
.imgshow{
  width: 80%;
  /*  margin-left: 10%; */
  margin-top: 1%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-auto-rows: 300px;
  grid-gap: 25px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin-bottom: 5%;
  padding: 1.25rem;
}

.imgbtn{
  display: flex;
  align-items: center;
  justify-content: space-around;
  text-align: center;
  border: #D3DCE6 0.0625rem solid;
  padding: 1.25rem;
  border-radius: 0.625rem;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.deletebtn{
  margin-left: 0.625rem;
}
//上传部分
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;

  }

.el-upload.el-upload--text{
  width: 100% !important;
  height: 100% !important;

}
.el-icon-plus.avatar-uploader-icon{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
