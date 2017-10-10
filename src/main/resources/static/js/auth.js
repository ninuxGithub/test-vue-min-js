
const SERVER_URL = 'http://localhost'
const LOGIN_URL = SERVER_URL + '/oauth/token'


const auth={
	 	data: {
	        authenticated: false
	    },
	    login(context,info){
	        context.$http.post(LOGIN_URL,info).then(function(data){
	        	//console.dir(data)
	        	//console.dir(data.data['access_token'])
	            //var jsonData = JSON.parse(data.response);
	            //localStorage.setItem('token',jsonData['access_token']);
	        	localStorage.setItem('token',data.data['access_token']);
	        	
	            this.authenticated = true
	            localStorage.setItem('authenticated',authenticated);
	            context.$router.push({path:'/home', query:{'flag': this.authenticated}})
	            //this.$router.push({path:'/home', query:{'flag': this.authenticated}})
	        },function(err){
	            console.log(err + "," +err.body.message)
	            context.error = err.body.message
	            this.authenticated = true
	            localStorage.setItem('authenticated',authenticated);
	        })
	    },
	    getAuthHeader(){
	        return {
	            'Authorization':'Bearer ' + localStorage.getItem('token')
	        }
	    },
	    getAuthenticated(){
	    	return this.authenticated;
	    },
	    checkAuth(){
	        var token = localStorage.getItem('token')
	        if(token){
	            this.authenticated = true
	        }else{
	            this.authenticated = false
	        }
	    }
	
}
