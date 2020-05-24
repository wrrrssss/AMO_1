import Vue from 'vue'
import Router from 'vue-router'
import Homepage from '../components/Homepage/Homepage'
import Search from "../components/Homepage/Search";
import Recommend from "../components/Homepage/Recommend";
import Login from '../components/Login/Login'
import Register from '../components/Register/Register'

/*个人空间相关*/
import Space from '../components/Space/Space'

/*消息相关*/
import Message from '../components/Space/Message/Message'
import MyMessage from "../components/Space/Message/MyMessage";
import MyMessageDetail from "../components/Space/Message/MyMessageDetail";
import Reply from "../components/Space/Message/Reply";
import ThumpUp from "../components/Space/Message/ThumpUp";

import Dynamics from '../components/Space/Dynamics/Dynamics'
import Collection from '../components/Space/Collections/Collection'
import CollectionDetail from '../components/Space/Collections/CollectionDetail'

import Manuscript from '../components/Space/Manuscript/Manuscript'
import MsData from '../components/Space/Manuscript/MsData'
import Editing from '../components/Space/Manuscript/Editing'
import ManuManagement from '../components/Space/Manuscript/ManuManagement/ManuManagement'
import Manu from '../components/Space/Manuscript/ManuManagement/Manu'
import Video from '../components/Space/Manuscript/ManuManagement/Video'

import Friend from '../components/Space/Friend/Friend'
import Fans from '../components/Space/Friend/Fans'
import Focus from '../components/Space/Friend/Focus'

import History from "../components/Space/History/History";
import Edit from "../components/Space/Edit/Edit";

import Article from '../components/Articles/Article'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Homepage',
      component: Homepage
    },
    {
      path:'/search',
      component:Search,
    },
    {
      path:'/recommend',
      component:Recommend,
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path:'/space',
      name:'Space',
      component:Space,
      children:[
        {
          path:'message',
          component:Message,
          children:[
            {
              path:'reply',
              component:Reply,
            },
            {
              path:'thumps',
              component:ThumpUp,
            },
            {
              path:'mymessage',
              component:MyMessage,
              children:[
                {
                  path: ':id',
                  component: MyMessageDetail
                }
              ]
            }
          ]
        },
        {
          path: 'dynamics',
          component: Dynamics
        },
        {
          path:'collections',
          component:Collection,
          children:[
            {
              path:'detail/:id',
              component:CollectionDetail,
            }
          ]
        },
        {
          path:'manuscript',
          component:Manuscript,
          children:[
            {
              path:'msdata',
              component: MsData,
            },
            {
              path:'edit',
              component:Editing
            },
            {
              path:'management',
              component:ManuManagement,
              children:[
                {
                  path:'manu',
                  component:Manu,
                },
                {
                  path:'video',
                  component: Video,
                }
              ]
            },
          ]
        },
        {
          path:'friend',
          component:Friend,
          children:[
            {
              path: 'focus/:id',
              component: Focus,
            },
            {
              path:'fans',
              component: Fans,
            }
          ]
        },
        {
          path:'history',
          component:History,
        },
        {
          path:'edit',
          component:Edit
        }
      ]
    },
    {
      path:'/article/:id',
      component:Article
    },

  ]
})
