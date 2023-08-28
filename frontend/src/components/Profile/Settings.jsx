import React, { useRef, useState } from "react";
import { Button, Col, Container, Form, Image, Row } from "react-bootstrap";
import { BsEnvelopeFill } from "react-icons/bs";
import Imagenes from "../../assets/imagenes";

const Settings = () => {
	const [selectFile, setSelectFile] = useState(null);
	const fileInputRef = useRef(null);

	const handleFileChange = event => setSelectFile(event.target.files[0]);
	const handleUploadButtonClick = () => fileInputRef.current.click();
	return (
		<Container>
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
					<Row md={2}>
						<Form.Group className='mb-4' controlId='formGroupEmail'>
							<Row md={3}>
								<Form.Label>
									{" "}
									<BsEnvelopeFill /> Email
								</Form.Label>
								<Form.Check
									disabled
									checked
									type='checkbox'
									id='custom-switch'
									label='verified'
									style={{ marginLeft: "auto" }}
								/>
							</Row>
							<Form.Control type='email' placeholder={"Enter email"}></Form.Control>
						</Form.Group>
						<Form.Group className='mb-3' controlId='formGroupPassword'>
							<Form.Label>Password</Form.Label>
							<Form.Control type='password' placeholder='Password' />
						</Form.Group>
					</Row>
					<Row md={2}>
						<Form.Group className='mb-3' controlId='formGroupEmail'>
							<Form.Label>Username</Form.Label>
							<Form.Control type='password' placeholder='Username' />
						</Form.Group>
						<Form.Group className='mb-3' controlId='formGroupEmail'>
							<Form.Label>Contraseña</Form.Label>
							<Form.Control type='password' placeholder='Contraseña' />
						</Form.Group>
					</Row>
					{/* Crear un boton */}
					<Button variant='primary' type='submit'>
						Update Profile
					</Button>
				</Form>
			</Col>
		</Container>
	);
};

export default Settings;
