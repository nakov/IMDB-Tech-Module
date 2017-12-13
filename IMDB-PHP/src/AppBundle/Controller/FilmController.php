<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Film;
use AppBundle\Form\FilmType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class FilmController extends Controller
{
    /**
     * @param Request $request
     * @Route("/", name="index")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function index(Request $request)
    {
        $repo = $this->getDoctrine()->getRepository(Film::class);
        $films = $repo->findAll();
        return $this->render(
            ":film:index.html.twig",
            ["films" => $films]
        );
    }

    /**
     * @param Request $request
     * @Route("/create", name="create")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function create(Request $request)
    {
        $film = new Film();
        $form = $this->createForm(FilmType::class, $film);
        $form->handleRequest($request);
        $errorMsg = "";
        if ($form->isSubmitted()) {
            if ($form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->persist($film);
                $em->flush();
                return $this->redirect("/");
            } else
                $errorMsg = "Invalid form data";
        }
        return $this->render(
            ":film:create.html.twig",
            ["film" => $film, "form" => $form->createView(),
                "errorMsg" => $errorMsg]
        );
	}

    /**
     * @Route("/edit/{id}", name="edit")
     *
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function edit($id, Request $request)
    {
        $filmRepo = $this->getDoctrine()->getRepository(Film::class);
        $film = $filmRepo->find($id);
        if ($film == null) {
            return $this->redirect("/");
        }

        $form = $this->createForm(FilmType::class, $film);
        $form->handleRequest($request);
        $errorMsg = "";
        if ($form->isSubmitted()) {
            if ($form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->merge($film);
                $em->flush();
                return $this->redirect("/");
            } else
                $errorMsg = "Invalid form data";
        }
        return $this->render(
            ":film:edit.html.twig",
            ["film" => $film, "form" => $form->createView(),
                "errorMsg" => $errorMsg]
        );
    }

    /**
     * @Route("/delete/{id}", name="delete")
     *
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function delete($id, Request $request)
    {
        $filmRepo = $this->getDoctrine()->getRepository(Film::class);
        $film = $filmRepo->find($id);
        if ($film == null) {
            return $this->redirect("/");
        }

        $form = $this->createForm(FilmType::class, $film);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($film);
            $em->flush();
            return $this->redirect("/");
        }
        return $this->render(
            ":film:delete.html.twig",
            ["film" => $film, "form" => $form->createView()]
        );
    }
}
