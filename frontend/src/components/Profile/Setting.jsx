import React, { useState } from "react";
import { Form, Container, Col, Image, Button, Card, Nav } from "react-bootstrap";
import "./Profile.css";
import Settings from "./views/Settings.jsx";
import LoginAndSecutiry from "./views/LoginAndSecutiry.jsx";
import AdditionalSettings from "./views/AdditionalSettings.jsx";
import About from "./views/About.jsx";
import { menuItems } from "../../config/Data/ArraysItems.js";

function Setting() {
	const [activeTab, setActiveTab] = useState("#account");

	const handleTabClick = eventKey => setActiveTab(eventKey);
	return (
		<Card className='card_profile'>
			<Card.Header style={{ background: "transparent" }}>
				<Nav
					variant='pills'
					defaultActiveKey='#account'
					activeKey={activeTab}
					onSelect={handleTabClick}
				>
					{menuItems.map((item) => (
						<Nav.Item key={item.href}>
							<Nav.Link href={`#${item.href}`} className='nav-link'>
								{item.label}
							</Nav.Link>
						</Nav.Item>
					))}
				</Nav>
			</Card.Header>
			<Card.Body>
				{activeTab === "#account" && <Settings />}
				{activeTab === "#security" && <LoginAndSecutiry />}
				{activeTab === "#additionalSettings" && <AdditionalSettings />}
				{activeTab === "#about" && <About />}
			</Card.Body>
		</Card>
	);
}

export default Setting;
