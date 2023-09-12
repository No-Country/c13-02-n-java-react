import React, {useEffect, useRef, useState} from "react";
import { Button, Col, Container, Form, Image, Row } from "react-bootstrap";
import { BsEnvelopeFill } from "react-icons/bs";
import Imagenes from "../../../assets/imagenes.jsx";
// import {formGroups} from "../../../config/models/ArraysItems.js";
import axios from "axios";
import userProfile from "../../../services/products.js";
import {getLocalStorageIdUtil} from "../../../config/utils/getLocalStorageIdUtil.js";

const Settings = () => {
	const [selectFile, setSelectFile] = useState(null);
	const [userData, setUserData] = useState({}); // Para almacena los datos del usuario
	const fileInputRef = useRef(null);
	const handleFileChange = event => setSelectFile(event.target.files[0]);
	const handleUploadButtonClick = () => fileInputRef.current.click();
	const AxiosUserData = async () => {
		try {
			// Todo: consumir la API para obtener los datos del usuario
			const id = getLocalStorageIdUtil("id")
			const userData = await userProfile.getUser(`users/${id}`);
			setUserData(userData);
			console.log(userData);
		} catch (error) {
			console.log(error);
		}
	}

	// Cargar los datos del usuario
	useEffect(() => {
		AxiosUserData().then(r => console.log(r));
	}, []);

	return (
		<Container>
			{/* Imagen */}
			<Col xs={1} sm={2} md={12} className='image__profile'>
				<Image
					src={selectFile ? URL.createObjectURL(selectFile) : Imagenes.perfil2}
					alt='Perfil'
					roundedCircle
					style={{width: "100px", height: "100px"}}
				/>
				<input
					type='file'
					accept='image/*'
					onChange={handleFileChange}
					style={{ display: "none" }}
					ref={fileInputRef}
				/>
				<Button variant='info' onClick={handleUploadButtonClick}>
					Upload new
				</Button>{" "}
				<Button variant='secondary'>Remove profile</Button>
			</Col>
			<hr />
			<Col>
				<Form xs={1} md={12} className='g-4'>
					<Row md={2}>
						<Form.Group className='mb-3' controlId='formGroupUsername'>
							<Form.Label>FullName</Form.Label>
							<Form.Control type='text' placeholder={userData.fullName || 'FullName'} disabled={true}/>
						</Form.Group>
						<Form.Group className='mb-3' controlId='formGroupUsername'>
							<Form.Label>Email</Form.Label>
							<Form.Control type='text' placeholder={userData.username || 'Username'} disabled={true}/>
						</Form.Group>
					</Row>
					<Row md={2}>
						<Form.Group className='mb-3' controlId='formGroupUsername'>
							<Form.Label>Business Name</Form.Label>
							<Form.Control type='text' placeholder={userData.businessName || 'Business Name'}
										  disabled={true}/>
						</Form.Group>
						<Form.Group className='mb-3' controlId='formGroupUsername'>
							<Form.Label>Password</Form.Label>
							<Form.Control type='password' placeholder={'**********'}
										  disabled={true}/>
						</Form.Group>
					</Row>
					<Row md={2}>
						<Form.Group className='mb-3' controlId='formGroupUsername'>
							<Form.Label>Phone</Form.Label>
							<Form.Control type='text' placeholder={userData.phone || 'Phone'}
										  disabled={true}/>
						</Form.Group>
					</Row>
					<div className="d-flex justify-content-end">
						<Button variant='primary' type='submit'>
							Update Profile
						</Button>
					</div>
				</Form>
			</Col>
		</Container>
	);
};


export default Settings;
