import React , {forwardRef} from 'react'
import './Post.css'
import avatar_logo from "./images/avatar_logo.png";
import post_img from "./images/post_img.jpg";
import { Avatar } from '@mui/material';
import VerifiedIcon from '@mui/icons-material/Verified';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import RepeatIcon from '@mui/icons-material/Repeat';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import PublishIcon from '@mui/icons-material/Publish';


const Post = forwardRef(
    ({ displayName,userName,verified, timestamp, text, image,  avatar}, ref) => {

  return (
   <div className='post' ref={ref}> 
    <div className='post__avatar'>
        <Avatar src={avatar}/> 
    </div>
    <div className='post__body'>
        <div className='post__header'>
            <div className='post__headerText'>
                <h3>
                    {displayName} {''} 
                    <span className="post__headerSpecial">
                        
                        {verified && <VerifiedIcon className="post__badge"/>}
                        
                        @{userName}
                        </span>
                </h3>
            </div>
            <div className='post__headerDescription'>
                <p>{text}</p>
            </div>
        </div>
        <img src={image} className='post__image'/>
        <div className='post__footer'>
            <ChatBubbleOutlineIcon fontSize="small"/>
            <RepeatIcon fontSize="small"/>
            <FavoriteBorderIcon fontSize="small"/>
            <PublishIcon fontSize="small"/>
        </div>
    </div>
  </div>
  )
});

export default Post