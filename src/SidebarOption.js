import React from "react";
import "./SidebarOption.css";

function SidebarOption({active ,text , Icon}) {
  return (
    <div className={`sidebarOption ${active && 'sidebarOption--active'}`}>
        <div className="sidebarOption__container">
         {Icon && <Icon />}
        <h2>{text}</h2>
        </div>
    </div>
  );
}

export default SidebarOption;