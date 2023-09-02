import React, {useEffect, useRef, useState} from "react";
import { Button, Col, Container, Form, Image, Row } from "react-bootstrap";
import { BsEnvelopeFill } from "react-icons/bs";
import Imagenes from "../../../assets/imagenes.jsx";
import { formGroups } from "../../../config/Data/ArraysItems.js";
import axios from "axios";
import {request} from "../../../config/helpers/axios_helper.jsx";

const Settings = () => {
	const [selectFile, setSelectFile] = useState(null);
	const [userData, setUserData] = useState({}); // Para almacena los datos del usuario
	const fileInputRef = useRef(null);

	const handleFileChange = event => setSelectFile(event.target.files[0]);
	const handleUploadButtonClick = () => fileInputRef.current.click();

	// Funcion para cargar los datos del usuario
	const fetchUserData = async () => {
		try {
			const response = await request("GET", "/users/1");
			if(response.ok) {
				const data = await response .json();
				setUserData(data);
			} else {
				console.log("Error al cargar los datos del usuario");
			}
		} catch (error) {
			console.log(error);
		}
	}

	// Cargar los datos del usuario
	useEffect(() => {
		fetchUserData();
	}, []);

	return (
		<Container>
			{/* Imagen */}
			<Col xs={1} sm={2} md={12}>
				<Image
					src={selectFile ? URL.createObjectURL(selectFile) : Imagenes.perfil2}
					alt='Perfil'
					roundedCircle
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
					{formGroups.map((group, index) => (
						<Row md={2} key={index}>
							{group.map((field, fieldIndex) => (
								<Form.Group className='mb-3' controlId={`formGroup${index}-${fieldIndex}`} key={fieldIndex}>
									<Form.Label>{field.label}</Form.Label>
									<Form.Control type={field.type} placeholder={field.placeholder} value={userData[field.label]} />
								</Form.Group>
							))}
						</Row>
						))}
					{/* Boton */}
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
