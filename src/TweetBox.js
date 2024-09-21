import React, { useState } from 'react'
import "./TweetBox.css"
import avatar_logo from "./images/avatar_logo.png";
import { Avatar , Button } from '@mui/material';
import db from './firebase';


function TweetBox() {
    const[tweetMessage, setTweetMessage] = useState('');
    const[tweetImage, setTweetImage] = useState('');

    const sendTweet = e => {
        e.preventDefault();

        db.collection('posts').add({
            displayName : "Dilshan Gamage",
            userName : 'houralabs',
            verified : true,
            text : tweetMessage,
            image : tweetImage,
            avatar : avatar_logo 
        });
        setTweetMessage("");
        setTweetImage("");
    }

  return <div className='tweetBox'>
        <form>
            <div className='tweetBox__input'>
                <Avatar src={avatar_logo}/>
                <input
                onChange={(e) => setTweetMessage(e.target.value)} 
                value={tweetMessage} 
                placeholder="What's happening?" 
                type='input'/>
            </div>
            <input 
            value={tweetImage}
            onChange={(e) => setTweetImage(e.target.value)}
            placeholder="Optional : Enter image URL" 
            type='input' 
            className='tweetBox__imageInput'/>

            <Button onClick={sendTweet} type='submit' className='tweetBox__tweetButton'>Tweet</Button>
        </form>
    </div>;
}

export default TweetBox