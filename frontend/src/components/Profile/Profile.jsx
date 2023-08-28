import React, { useState } from "react";
import { Form, Container, Col, Image, Button, Card, Nav } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Profile.css";
import Settings from "./Settings";
import LoginAndSecutiry from "./LoginAndSecutiry";
import AdditionalSettings from "./AdditionalSettings";
import About from "./About";

function Profile() {
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
					<Nav.Item>
						<Nav.Link href='#account' className='nav-link'>
							Account Settings
						</Nav.Link>
					</Nav.Item>
					<Nav.Item>
						<Nav.Link href='#security' className='nav-link'>
							Login & Security
						</Nav.Link>
					</Nav.Item>
					<Nav.Item>
						<Nav.Link href='#additionalSettings' className='nav-link'>
							Additional Settings
						</Nav.Link>
					</Nav.Item>
					<Nav.Item>
						<Nav.Link href='#about' className='nav-link'>
							About
						</Nav.Link>
					</Nav.Item>
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

export default Profile;
