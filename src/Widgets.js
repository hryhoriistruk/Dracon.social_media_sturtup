import React from 'react'
import "./Widgets.css"
import {TwitterTimelineEmbed , TwitterShareButton , TwitterTweetEmbed} from "react-twitter-embed"; 
import SearchIcon from '@mui/icons-material/Search';

function Widgets() {
  return (
    <div className='widgets'>
        <div className='widgets__input'>
            <SearchIcon className="widgets__searchIcon" />
            <input placeholder='Search Twitter' type='text'/>
        </div>

        <div className='widgets__widgetsContainer'>
            <h2>What's happening</h2>

            <TwitterTweetEmbed tweetId='1671329161431109632'/>

            <TwitterTimelineEmbed sourceType="profile"
            screenName='elonmusk'
            options={{innerHeight:400}}/>

            <TwitterShareButton
                url={"https://facebook.com/cleverprogrammer"}
                options={{text: "#reactjs is awesome", via:"cleverqazi"}}
            />

        </div>
    </div>
  );
}

export default Widgets