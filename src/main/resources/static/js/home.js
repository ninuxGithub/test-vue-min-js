function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
const Home ={
	template:`<div id="home">
			    <h1>{{msg}}</h1>
			    <button @click="getUsers()">Get Users</button>
			    <h2>userInfo:{{userInfo}}</h2>
			  </div>`,
	name: 'home',
	  data () {
	    return {
	      msg: '欢迎您登录成功',
	      userInfo:''
	    }
	    },
	    beforeCreate(){
	        //如果没有token的话需要重新登录
	    	//console.dir(auth.data.authenticated)
	    	var authenticated = localStorage.getItem('authenticated')
	    	console.dir("beforeCreate :"+authenticated)
	        if(!authenticated){
	            this.$router.push('login')
	        }
	    },
	    methods:{
	        getUsers(){
	        	var that = this;
	        	$.ajax({
	        		  url: "http://localhost/api/users/",
	        		  data: {},
	        		  type: "GET",
	        		  beforeSend: function(xhr){
	        			  //这里设置header
	        			  xhr.setRequestHeader('Authorization','Bearer ' + localStorage.getItem('token'));
	        		  },
	        		  success: function(jsonData) {
	        			  console.dir(jsonData)
	        			  that.userInfo = jsonData;
	        			  
	        		  }
	        		});
//	            this.$http.get('http://localhost/api/users/',{
//	                headers:auth.getAuthHeader()
//	            }).then(function(re){
//	            	console.dir(re)
//	                this.userInfo = re.bodyText
//	            },function(){
//	                console.log("get email error")
//	            })
	        }
	    }

}