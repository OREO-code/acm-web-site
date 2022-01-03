<template>
	<div>
		<div>当前公告内容预览</div>
		<marquee class="player"  style="color: ivory;" >
			公告:{{notice}}
		</marquee>
		<el-input
		  placeholder="请输入公告编辑内容"
		  v-model="input"
		  clearable>
		</el-input>
		<el-button type="primary">提交</el-button>
	</div>
</template>

<script>
	export default {
	    data() {
	      return {
	        activeIndex: '1',
	        activeIndex2: '1',
			showImageList:[],
			notice:'',
			token:'',
			input: ''
	      };
	    },
		mounted() {
		    
		},
		created() {
			var _that=this
			this.$axios.get('http://101.43.16.42:8082/notice').then((response)=>{
					_that.notice=response.data.data.notice
			        console.log(response);
			      }).catch((response)=>{
			        console.log(response);
			      })
		},
		methods:{
		},
		}
</script>

<style>
	.title{
		width: 80%	;
		margin-left: 10%;
		}
	.player{
		width: 100%;
		height: 3.125rem;
		filter: Alpha(Opacity=50); /* 针对IE*/
		background-color: #003A79; 
		top: 0%;
		line-height: 3.125rem;
	}
</style>
