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
import MyChat from "../components/Space/Message/MyChat";
import MyChatDetail from "../components/Space/Message/MyChatDetail";
import Mymessage from "../components/Space/Message/MyMessage";


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

import OtherSpace from "../components/OtherSpace/OtherSpace";
import OtherCollection from "../components/OtherSpace/Collections/OtherCollection";
import OtherCollectionDetail from "../components/OtherSpace/Collections/OtherCollectionDetail";
import OtherDynamics from "../components/OtherSpace/Dynamics/OtherDynamics";
import OtherHistory from "../components/OtherSpace/History/OtherHistory";
import OtherFriend from "../components/OtherSpace/Friend/OtherFriend";
import OtherFans from "../components/OtherSpace/Friend/OtherFans";
import OtherFocus from "../components/OtherSpace/Friend/OtherFocus";
import OtherManuscript from "../components/OtherSpace/Manuscript/OtherManuscript";

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
              path:'message',
              component:Mymessage,
            },
            {
              path:'chat',
              component:MyChat,
              children:[
                {
                  path: ':id',
                  component: MyChatDetail
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
    {
      path:'/space/:userId',
      component:OtherSpace,
      children:[
        {
          path:'dynamics',
          component:OtherDynamics,
        },
        {
          path:'manuscript',
          component:OtherManuscript,
        },
        {
          path:'collections',
          component:OtherCollection,
          children:[
            {
              path:'detail/:id',
              component:OtherCollectionDetail,
            }
          ]
        },
        {
          path:'friend',
          component:OtherFriend,
          children:[
            {
              path: 'focus/:id',
              component: OtherFocus,
            },
            {
              path:'fans',
              component: OtherFans,
            }
          ]
        },
        {
          path:'history',
          component:OtherHistory,
        }
      ]
    }
  ]
})
